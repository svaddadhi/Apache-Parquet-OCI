package helidon.oci.library;

import library.FilterColObject;
import library.FilterRowObject;

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
 * Table must already be created
 *
 * Filter a file:
 * curl -X PUT -H "Content-Type: application/json" -d '{"filePath" : "/home/phvle/nation.parquet", "columns" : ["N_NATIONKEY", "N_NAME"], "tableName" : "phuTable", "vals" : [0, "Europe"], "tar" : "/home/phvle/target.csv"}' http://localhost:8080/filter/row
 *
 * File location:
 * Target Parquet file can be found in /tmp/{tableName}
 */
@Path("/filter/row")
@RequestScoped
public class FilterRowResource {
    /***
     * Filters certain rows out of a Parquet file into another Parquet file
     * @param jsonObject
     * @return
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response filterFile(JsonObject jsonObject) {
        String src = jsonObject.getString("filePath");
        JsonArray col_data = jsonObject.getJsonArray("columns");
        String tableName = jsonObject.getString("tableName");
        JsonArray val_data = jsonObject.getJsonArray("vals");
        String tar = jsonObject.getString("tar");

        String[] vals = new String[val_data.size()];
        for(int i = 0; i < val_data.size(); i++) {
            vals[i] = val_data.getString(i);
        }

        String[] columns = new String[col_data.size()];
        for(int i = 0; i < col_data.size(); i++) {
            columns[i] = col_data.getString(i);
        }

        try {
            FilterRowObject object = new FilterRowObject(src, columns, vals, tableName, tar);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return Response.ok().build();
    }
}
