package library;

import library.transformation.NativeTransform;
import library.transformation.ParquetTransform;

import java.sql.SQLException;

public class ConvertObject {

    /* Constructor for converting CSV -> Parquet */
    public ConvertObject(String src, String tar) throws SQLException, ClassNotFoundException {
        System.out.println("asdf");
        ParquetTransform obj = new NativeTransform(src, tar);
        obj.convertToParquet();
    }

    /* Constructor for converting Parquet -> CSV */
    public ConvertObject(String src, String tar, int len) {
        ParquetTransform obj = new NativeTransform(src, tar, len);
        obj.convertToCSV();
    }

    public void test() {
        System.out.println("here?");
    }
}
