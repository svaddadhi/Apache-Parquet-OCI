package helidon.oci.library;

import library.OciObject;

import javax.enterprise.context.RequestScoped;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/upload")
@RequestScoped
public class UploadResource {
    // curl -X PUT -H "Content-Type: application/json" -d '{"filePath" : "/home/phvle/file_to_convert.parquet"}' http://localhost:8080/upload/axovcbqne66q/sample-bucket/file_to_convert.parquet

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{namespace}/{bucket}/{object}")
    public Response uploadFile(@PathParam("namespace") String namespace,
                             @PathParam("bucket") String bucket,
                             @PathParam("object") String object, JsonObject jsonObject) throws Exception {


        String filePath = jsonObject.getString("filePath");

        try {
            OciObject upload = new OciObject();
            upload.upload(namespace, bucket, object, filePath);
        }
        catch(Exception e) {
            if(Integer.parseInt(e.getMessage().substring(1, 4)) == 404) {
                throw new FileNotFoundException(object);
            } else {
                return Response.status(500).build();
            }
        }

        return Response.ok().build();
    }

}
