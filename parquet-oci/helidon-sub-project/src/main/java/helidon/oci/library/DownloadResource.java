package helidon.oci.library;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/download")
@RequestScoped
public class DownloadResource {
    @GET
    public String downloadFile() {
        return "Hello World";
    }
}
