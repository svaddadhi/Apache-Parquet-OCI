package library;

import library.transformation.NativeTransform;
import library.transformation.ParquetTransform;

import java.sql.SQLException;

public class ConvertObject {

    /* Constructor for converting CSV -> Parquet */
    public ConvertObject(String src, String tar) throws SQLException, ClassNotFoundException {
        ParquetTransform obj = new NativeTransform();
        obj.convertToParquet(src, tar);
    }

    /* Constructor for converting Parquet -> CSV */
    public ConvertObject(String src, String tar, int len) {
        ParquetTransform obj = new NativeTransform();
        obj.convertToCSV(src, tar, len);
    }
}
