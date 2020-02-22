package helidon.oci.library;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/upload")
@RequestScoped
public class UploadResource {
    @GET
    public String uploadFile() {
        return "Hello World";
    }
}
