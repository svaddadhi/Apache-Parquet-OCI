package library;

import library.service.GetPropertyValues;
import library.transformation.DrillTransform;
import library.transformation.ParquetTransform;

import java.io.IOException;
import java.sql.SQLException;

public class FilterObject {
    /***
     * Filters a Parquet file
     * @param src
     * @param columns
     * @param tableName
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public FilterObject(String src, String[] columns, String tableName) throws SQLException, ClassNotFoundException, IOException {
        GetPropertyValues propertyObj = new GetPropertyValues();
        ParquetTransform object = new DrillTransform(src, propertyObj.getPropValue("host"), propertyObj.getPropValue("clusterId"));
        object.filterByColumns(tableName,columns, columns.length, src);
    }
}
