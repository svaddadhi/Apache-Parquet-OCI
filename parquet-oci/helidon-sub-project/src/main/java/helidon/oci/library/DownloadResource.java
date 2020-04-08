package helidon.oci.library;

import library.DownloadObject;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.File;
import java.util.logging.Logger;


@Path("/download")
@RequestScoped
public class DownloadResource {
    // curl -X GET http://localhost:8080/download/axovcbqne66q/sample-bucket/sample1.csv

    private final static Logger LOGGER = Logger.getLogger(DownloadResource.class.getName());

    @GET
    @Path("{namespace}/{bucket}/{object}")
    public Response downloadFile(@PathParam("namespace") String namespace,
                                 @PathParam("bucket") String bucket,
                                 @PathParam("object") String object){

        // TODO: make filePath configurable
        String filePath = System.getProperty("user.home") + File.separator + object;

        try {
            DownloadObject downloadObj = new DownloadObject(namespace, bucket, object, filePath);
            downloadObj.download();
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
