package helidon.oci.library;

import library.FilterObject;
import net.minidev.json.JSONArray;

import javax.enterprise.context.RequestScoped;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Path("/filter")
@RequestScoped
public class FilterResource {

    // curl -X PUT -H "Content-Type: application/json" -d '{"filePath" : "/home/phvle/nation.parquet", "columns" : ["N_NATIONKEY", "N_NAME"], "tableName" : "phuTable"}' http://localhost:8080/filter

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
            FilterObject object = new FilterObject(src, columns, tableName);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return Response.ok().build();
    }
}
