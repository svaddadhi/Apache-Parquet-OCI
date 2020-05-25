package library.transformation;

import java.io.IOException;
import java.sql.SQLException;

public interface ParquetTransform {
    void filterByColumns();
    void filterByRows(String table, String colName[], String val[], String tar) throws IOException, SQLException;
    void convertToParquet(String src, String localCopy, String dest) throws SQLException;
    void convertToParquet(String tableName, String[] columns, int len, String src) throws SQLException;
    void filterByColumns(String tableName, String[] columns, int len, String src) throws SQLException;
    void convertToCSV(String src, String dest, int len);
}
