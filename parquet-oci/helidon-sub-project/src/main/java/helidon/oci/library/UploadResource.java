package helidon.oci.library;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/upload")
@RequestScoped
public class UploadResource {
    @GET
    public String uploadFile() throws Exception {
        String namespaceName = "axvsvpirtkel";
        String bucketName ="bucket-20200129-1839";
        String objectName = "example2.csv";
        String body = "/home/david/Descargas/example2.csv";

        UploadObject upload=new UploadObject(namespaceName, bucketName, objectName, body);
        upload.upload();

        return "Hello World";
    }
}
