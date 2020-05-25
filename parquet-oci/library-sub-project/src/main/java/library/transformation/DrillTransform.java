package library.transformation;

import library.drill.Drill;
import library.service.AddUtil;

import java.io.IOException;
import java.sql.*;
import java.lang.*;

public class DrillTransform implements ParquetTransform {
    String src;
    String dest;
    Drill obj;

    public DrillTransform(String src, String host, String prot) throws SQLException, ClassNotFoundException {
        this.src = src;
        this.dest = dest;
        obj = new Drill(host, prot).connect().extExecutor();
    }

    @Override
    public void filterByColumns() {

    }

    @Override
    public void filterByColumns(String tableName, String[] columns, int len, String src) throws SQLException {
        obj.filter(tableName, columns, len, src);
    }

    @Override
    public void filterByRows() {

    }

    @Override
    public void convertToParquet(String tableName, String[] columns, int len, String src) throws SQLException {
        obj.convert(tableName, columns, len, new AddUtil(src));
    }

    @Override
    public void convertToParquet(String src, String localCopy, String dest) throws SQLException{
        this.obj.convert(dest, new AddUtil(localCopy, src));
    }

    @Override
    public void convertToCSV(String src, String dest, int len){
        try {
            this.obj.pull(src, dest);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
