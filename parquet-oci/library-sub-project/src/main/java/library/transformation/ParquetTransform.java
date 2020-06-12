package library.transformation;

import java.io.IOException;
import java.sql.SQLException;

/***
 * Parquet Transformations
 */
public interface ParquetTransform {
    void filterByColumns();
    void filterByRows(String table, String colName[], String val[], String tar) throws IOException, SQLException, InterruptedException;
    void convertToParquet(String src, String localCopy, String dest) throws SQLException, IOException;
    void convertToParquet(String tableName, String[] columns, int[] colNum, int len, String src) throws SQLException, IOException;
    void filterByColumns(String tableName, String[] columns, int len, String src) throws SQLException;
    void convertToCSV(String src, String dest, int len);
}
