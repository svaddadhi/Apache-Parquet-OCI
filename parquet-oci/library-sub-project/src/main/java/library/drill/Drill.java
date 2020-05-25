package library.drill;

import java.sql.*;

public class Drill {
    final private String host, protocol;
    private String  base, scheme;
    private Connection conn;
    private Statement st;

    public Connection getConn() {return this.conn;}
    public Statement getSt() {return this.st;}

    public Drill(String host, String prot) {
        this.host = host;
        this.protocol = prot;
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

    public Drill setConnProp(String base, String scheme) {
        this.base = base;
        this.scheme = scheme;
        return this;
    }

    public Drill convert(String name, String title[], int len, AddUtil au) throws SQLException {
        String sql = String.format("create table dfs.tmp.`%s` as select ", name);
        for (int i = 0; i < len; i++)
            sql += String.format("%s columns[%d] as `%s` ", i == 0 ? "" : ",", i, title[i]);
        sql += String.format("from dfs.`%s`", au.getAdd());
        System.out.println(sql);
        ResultSet rs = this.st.executeQuery(sql);
        while (rs.next()) System.out.println(rs.getString(1));
        return this;
    }

    public Drill convert(String name, AddUtil au) throws SQLException{
        String title[] = new CSVRP(au.getSvc()).open().readNext();
        return this.convert(name, title, title.length, au);
    }

    public Drill filter(String name, String title[], int len, String src) throws SQLException {
        String sql = String.format("create table dfs.tmp.`%s` as select ", name);
        for (int i = 0; i < len; i++)
            sql += String.format("%s `%s` ", i == 0 ? "" : ",", title[i]);
        sql += String.format("from dfs.`%s`", src);
        System.out.println(sql);
        ResultSet rs = this.st.executeQuery(sql);
        while (rs.next()) System.out.println(rs.getString(1));
        return this;
    }
}
