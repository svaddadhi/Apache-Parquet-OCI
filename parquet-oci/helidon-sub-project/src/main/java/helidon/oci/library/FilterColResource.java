package helidon.oci.library;

import library.FilterColObject;

import javax.enterprise.context.RequestScoped;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.sql.SQLException;

/**
 * A simple JAX-RS resource to filter columns from a Parquet file. Examples:
 *
 * Pre-Usage:
 * Apache Drill server must be running
 *
 * Filter a file:
 * curl -X PUT -H "Content-Type: application/json" -d '{"filePath" : "/home/phvle/nation.parquet", "columns" : ["N_NATIONKEY", "N_NAME"], "tableName" : {tableName}}' http://localhost:8080/filter/column
 *
 * File location:
 * Target Parquet file can be found in /tmp/{tableName}
 */
@Path("/filter/column")
@RequestScoped
public class FilterColResource {
    /***
     * Filters certain columns out of a Parquet file into another Parquet file
     * Target Parquet file can be found in /tmp/{tableName}
     * @param jsonObject
     * @return
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response filterFile(JsonObject jsonObject) {
        String src = jsonObject.getString("filePath");
        JsonArray col_data = jsonObject.getJsonArray("columns");
        String tableName = jsonObject.getString("tableName");

        String[] columns = new String[col_data.size()];
        for(int i = 0; i < col_data.size(); i++) {
            columns[i] = col_data.getString(i);
        }

        try {
            FilterColObject object = new FilterColObject(src, columns, tableName);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Response.ok().build();
    }
}
