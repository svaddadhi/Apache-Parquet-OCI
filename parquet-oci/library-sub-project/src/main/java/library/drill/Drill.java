package library.drill;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVWriter;
import library.c2p.CSVRP;
import library.drill.service.S3Transformation;
import library.service.AddUtil;
import library.service.util;
import org.apache.avro.generic.GenericData;

import static library.service.util.abort;

public class Drill {
    final private String host, protocol;
    final private String s3bucketMountingPoint = "s3bucket/drill";
    private Connection conn;
    private Statement st;

    public Connection getConn() {
        return this.conn;
    }

    public Statement getSt() {
        return this.st;
    }

    public Drill(String host, String prot) {
        this.host = host;
        this.protocol = prot;
    }

    public Drill close() throws SQLException {
        this.st.close();
        this.conn.close();
        this.st = null;
        this.conn = null;
        return this;
    }

    public Drill connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.apache.drill.jdbc.Driver");
        this.conn = DriverManager.getConnection("jdbc:drill:" + this.protocol + "=" + this.host);
        return this;
    }

    public Drill extExecutor() throws SQLException {
        this.st = this.conn == null ? null : this.conn.createStatement();
        return this;
    }

    public Drill convert(String name, String title[], int colNum[], int len, AddUtil au) throws SQLException {
        String sql = String.format("create table dfs.tmp.`%s` as select ", name);
        for (int i = 0; i < len; i++)
            sql += String.format("%s columns[%d] as `%s` ", i == 0 ? "" : ",", colNum[i], title[i]);
        sql += String.format("from dfs.`%s`", au.getAdd());
        System.out.println(sql);
        this.st.executeQuery(sql);
        return this;
    }

    public Drill convert(String name, AddUtil au) throws SQLException {
        String title[] = new CSVRP(au.getSvc()).open().readNext();
        int colNum[] = new int[title.length];
        for (int i = 0; i < title.length; i++) colNum[i] = i;
        return this.convert(name, title, colNum, title.length, au);
    }

    public Drill filter(String name, String title[], int len, String src) throws SQLException {
        String sql = String.format("create table dfs.tmp.`%s` as select ", name);
        for (int i = 0; i < len; i++)
            sql += String.format("%s `%s` ", i == 0 ? "" : ",", title[i]);
        sql += String.format("from dfs.`%s`", src);
        this.st.executeQuery(sql);
        return this;
    }

    public ResultSet exe(String sql) throws SQLException {
        return this.st.executeQuery(sql);
    }

    public ResultSet read(String table) throws SQLException {
        return this.exe(String.format("select * from dfs.tmp.`%s`", table));
    }

    public Drill pull(String src, String tar) throws SQLException, IOException {
        CSVWriter writer = new CSVWriter(new FileWriter(new File(tar)));
        List<String[]> csvCont = new ArrayList<>();
        ResultSet rs = this.read(src);
        while (rs.next()) {
            int len = rs.getMetaData().getColumnCount();
            String data[] = new String[len];
            for (int i = 0; i < len; i++) data[i] = rs.getString(i + 1);
            csvCont.add(data);
        }
        writer.writeAll(csvCont);
        writer.close();
        return this;
    }

    public Drill pull(String table, String colName[], String val[], String tar) throws SQLException, IOException, InterruptedException {

        /**
         * This function will perform filter on parquet database, and generate a csv file for user
         * which meets their expectation. Considering the executive machine does not have to be the
         * database server, we do not provide the automatic write back into the parquet database as
         * a brand new table.
         *
         * @param   table   The parquet target which we want to perform the filter
         * @param   colName The name of columns user want to apply the filter rule
         * @param   val     Expected value for each specified column
         * @param   tar     Target file location for the outputing csv file.
         * @ret     Drill   This function returns the object itself.
         */

        if (colName.length != val.length) abort("Row filter property not match");
        if (colName.length <= 0) return this.pull(table, tar);
        ResultSet rs = this.read(table);
        int len;
        if (!rs.next() || (len = rs.getMetaData().getColumnCount()) < colName.length) {
            abort("Request out of boundary");
            return this;
        }
        CSVWriter writer = new CSVWriter(new FileWriter(new File(tar)));
        List<String[]> csvCont = new ArrayList<>();
        int checkList[] = new int[len];
        String title[] = new String[len];

        for (int i = 0; i < len; i++) {
            checkList[i] = -1;
            title[i] = rs.getString(i + 1);
        }

        for (int k = 0; k < colName.length; k++)
            for (int i = 0; i <= len; i++) {
                if (i == len) abort("Column not found in row filter");
                if (title[i].equals(colName[k])) {
                    checkList[i] = k;
                    break;
                }
            }
        csvCont.add(title);
        while (rs.next()) {
            String cont[] = new String[len];
            for (int i = 0; i < len; i++) cont[i] = rs.getString(i + 1);
            boolean flag = true;
            for (int i = 0; flag && i < len; i++)
                flag = (checkList[i] == -1) || (checkList[i] != -1 && cont[i].equals(val[checkList[i]]));
            if (flag) csvCont.add(cont);
        }
        writer.writeAll(csvCont);
        writer.close();
        S3Transformation s3 = new S3Transformation(String.format("%s%s%s", System.getProperty("user.home"), File.separator, s3bucketMountingPoint), "/home/drill/s3bucket/drill", table + "_RS");
        if (!s3.execute(tar)) abort("Failed to copy file");
        return this;
    }
}
