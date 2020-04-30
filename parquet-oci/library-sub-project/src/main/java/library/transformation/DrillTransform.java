package library.transformation;

import java.sql.*;

public class DrillTransform implements ParquetTransform {
    String src;
    String dest;

    public DrillTransform(String src, String dest) {
        this.src = src;
        this.dest = dest;
    }

    @Override
    public void filterByColumns() {

    }

    @Override
    public void filterByRows() {

    }

    @Override
    public void convertToParquet() throws ClassNotFoundException, SQLException {

    }

    @Override
    public void convertToCSV(){};
}
