package library.transformation;

import java.sql.SQLException;

public interface ParquetTransform {
    void filterByColumns();
    void filterByRows();
    void convertToParquet() throws ClassNotFoundException, SQLException;
    void convertToCSV();
}
