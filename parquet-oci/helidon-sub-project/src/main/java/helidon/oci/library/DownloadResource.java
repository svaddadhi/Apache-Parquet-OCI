package helidon.oci.library;

import library.DownloadObject;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.File;


@Path("/download")
@RequestScoped
public class DownloadResource {
    // curl -X GET http://localhost:8080/download/axovcbqne66q/sample-bucket/sample1.csv

    @GET
    @Path("{namespace}/{bucket}/{object}")
    public Response downloadFile(@PathParam("namespace") String namespace,
                                 @PathParam("bucket") String bucket,
                                 @PathParam("object") String object) throws Exception {

        String filePath = System.getProperty("user.home") + File.separator + object;

        try {
            DownloadObject download = new DownloadObject(namespace, bucket, object, filePath);
            download.download();
        }
        catch(Exception e) {
            return Response.status(Integer.parseInt(e.getMessage().substring(1, 4))).build();
        }

        return Response.ok().build();
    }
}
