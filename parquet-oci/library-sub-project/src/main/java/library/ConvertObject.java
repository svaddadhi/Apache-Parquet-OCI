package library;

import library.p2c.Conv2Csv;
import library.c2p.Conv2Par;
import library.transformation.NativeTransform;
import library.transformation.Parquet;
import library.transformation.ParquetTransform;

import java.sql.SQLException;

public class ConvertObject {

    public ConvertObject(String src, String tar) throws SQLException, ClassNotFoundException {
        ParquetTransform obj = new NativeTransform(src, tar);
        obj.convertToParquet();
    }

    public ConvertObject(String src, String tar, int len) {
        ParquetTransform obj = new NativeTransform(src, tar, len);
        obj.convertToCSV();
    }
}
