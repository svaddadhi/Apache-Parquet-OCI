package library.transformation;

import java.sql.SQLException;

public interface ParquetTransform {
    void filterByColumns();
    void filterByRows();
    void convertToParquet(String src, String dest);
    void convertToParquet(String tableName, String[] columns, int len, String src) throws SQLException;
    void filterByColumns(String tableName, String[] columns, int len, String src) throws SQLException;
    void convertToCSV(String src, String dest, int len);
}
