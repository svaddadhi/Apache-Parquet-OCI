package library;

import library.service.GetPropertyValues;
import library.transformation.DrillTransform;
import library.transformation.ParquetTransform;

import java.io.IOException;
import java.sql.SQLException;

/***
 * Object file for filtering columns
 */
public class FilterColObject {
    /***
     * Filters a Parquet file
     * @param src
     * @param columns
     * @param tableName
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public FilterColObject(String src, String[] columns, String tableName) throws SQLException, ClassNotFoundException, IOException {
        GetPropertyValues propertyObj = new GetPropertyValues();
        ParquetTransform object = new DrillTransform(src, propertyObj.getPropValue("host"), propertyObj.getPropValue("clusterId"));
        object.filterByColumns(tableName,columns, columns.length, src);
    }

    public static void main(String [] args) throws SQLException, IOException, ClassNotFoundException {
        FilterColObject obj = new FilterColObject("/home/phvle/nation.parquet", new String[] {"N_NATIONKEY, N_NAME"}, "phuTable");
    }
}
