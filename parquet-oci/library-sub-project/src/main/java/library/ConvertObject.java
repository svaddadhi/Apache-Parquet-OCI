package library;

import library.transformation.NativeTransform;
import library.transformation.ParquetTransform;

import java.sql.SQLException;

public class ConvertObject {

    /***
     * Converts a CSV file to Parquet file
     * @param src
     * @param tar
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public ConvertObject(String src, String tar) throws SQLException, ClassNotFoundException {
        ParquetTransform obj = new NativeTransform();
        obj.convertToParquet(src, null, tar);
    }

    /***
     * Converts a Parquet file to CSV file
     * @param src
     * @param tar
     * @param len
     */
    public ConvertObject(String src, String tar, int len) {
        ParquetTransform obj = new NativeTransform();
        obj.convertToCSV(src, tar, len);
    }
}
