package helidon.oci.library;

import io.helidon.common.http.MediaType;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;

@Path("/download")
@RequestScoped
public class DownloadResource {
    private final GreetingProvider greetingProvider;

    @Inject
    public DownloadResource(GreetingProvider greetingConfig) {
        this.greetingProvider = greetingConfig;
    }

    @SuppressWarnings("checkstyle:designforextension")
    @GET
    //@Produces(MediaType.TEXT_PLAIN)
    public String downloadFile() {
        return "Hello World";
    }
}
