package library;

import library.service.GetPropertyValues;
import library.transformation.DrillTransform;
import library.transformation.ParquetTransform;

import java.io.IOException;
import java.sql.SQLException;

public class FilterRowObject {
    /***
     * Filters out certain rows of a Parquet file
     * @param src
     * @param columns
     * @param val
     * @param tableName
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public FilterRowObject(String src, String[] columns, String[] val, String tableName, String tar) throws SQLException, ClassNotFoundException, IOException, InterruptedException {
        GetPropertyValues propertyObj = new GetPropertyValues();
        ParquetTransform object = new DrillTransform(src, propertyObj.getPropValue("host"), propertyObj.getPropValue("clusterId"));
        object.filterByRows(tableName, columns, val, tar);
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException, InterruptedException, IOException {
        FilterRowObject object = new FilterRowObject("/home/phvle/nation.parquet", new String[] {"N_NATIONKEY","N_NAME"}, new String[] {"0","ALGERIA"},"phuTable","/home/phvle/nation.csv");
    }
}
