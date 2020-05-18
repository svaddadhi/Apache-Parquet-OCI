package library.transformation;

import library.drill.Drill;

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
        obj.newTable(tableName, columns, len, src);
    }

    @Override
    public void filterByRows() {

    }

    @Override
    public void convertToParquet(String tableName, String[] columns, int len, String src) throws SQLException {
        obj.newTable(tableName, columns, len, src);
    }

    @Override
    public void convertToParquet(String src, String dest) {

    }

    @Override
    public void convertToCSV(String src, String dest, int len){};
}
