package helidon.oci.library;

import library.OciObject;

import javax.enterprise.context.RequestScoped;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * A simple JAX-RS resource to upload files to OCI. Examples:
 *
 * Upload a file:
 * curl -X PUT -H "Content-Type: application/json" -d '{"filePath" : "/home/phvle/sample.csv"}' http://localhost:8080/upload/{namespace}/{bucket}/sample.csv
 *
 */
@Path("/upload")
@RequestScoped
public class UploadResource {
    /***
     * Upload a file to OCI
     * @param namespace
     * @param bucket
     * @param object
     * @param jsonObject
     * @return
     * @throws Exception
     */
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
