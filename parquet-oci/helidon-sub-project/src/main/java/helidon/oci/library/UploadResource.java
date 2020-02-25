package helidon.oci.library;

import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;

@Path("/upload")
@RequestScoped
public class UploadResource {
    private static final JsonBuilderFactory JSON = Json.createBuilderFactory(Collections.emptyMap());

    //curl -X PUT -H "Content-Type: application/json" -d '{"namespaceName" : "axvsvpirtkel", "bucketName" : "bucket-20200129-1839", "objectName" : "example2.csv", "body" : "/home/david/Descargas/example2.csv"}' http://localhost:8080/upload
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public String uploadFile(JsonObject jsonObject) throws Exception {
        String namespaceName = jsonObject.getString("namespaceName");
        String bucketName =jsonObject.getString("bucketName");
        String objectName = jsonObject.getString("objectName");
        String body = jsonObject.getString("body");

        UploadObject upload=new UploadObject(namespaceName, bucketName, objectName, body);
        upload.upload();

        return "uploaded";
    }

}
