package helidon.oci.library;

import library.OciObject;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.File;
import java.util.logging.Logger;


@Path("/download")
@RequestScoped
public class DownloadResource {
    /***
     * Downloads a file from OCI
     * @param namespace
     * @param bucket
     * @param object
     * @return
     * @throws Exception
     */
    @GET
    @Path("{namespace}/{bucket}/{object}")
    public Response downloadFile(@PathParam("namespace") String namespace,
                                 @PathParam("bucket") String bucket,
                                 @PathParam("object") String object) throws Exception {

        // TODO: make filePath configurable
        String filePath = System.getProperty("user.home") + File.separator + object;

        try {
            OciObject obj = new OciObject();
            obj.download(namespace, bucket, object, filePath);
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
