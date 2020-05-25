package library.transformation;

import library.c2p.Conv2Par;
import library.p2c.Conv2Csv;

import java.sql.SQLException;

public class NativeTransform implements ParquetTransform {

    @Override
    public void filterByColumns() {

    }

    @Override
    public void filterByRows() {

    }

    @Override
    public void convertToParquet(String tableName, String[] columns, int len, String src) throws SQLException {

    }

    @Override
    public void convertToParquet(String src, String sth, String dest) {
        new Conv2Par(src, dest).init().conv().close();
    }

    @Override
    public void filterByColumns(String tableName, String[] columns, int len, String src) throws SQLException {

    }

    @Override
    public void convertToCSV(String src, String dest, int len){ new Conv2Csv(src, dest, len).init().conv().close(); };
}
