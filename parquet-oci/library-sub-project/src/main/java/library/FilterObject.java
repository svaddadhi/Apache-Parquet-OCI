package library;

import library.transformation.DrillTransform;
import library.transformation.ParquetTransform;

import java.sql.SQLException;

public class FilterObject {
    public FilterObject(String src, String[] columns, String tableName) throws SQLException, ClassNotFoundException {
        ParquetTransform object = new DrillTransform(src, "localhost", "drillbit");
        object.filterByColumns(tableName,columns, columns.length, src);
    }
}
