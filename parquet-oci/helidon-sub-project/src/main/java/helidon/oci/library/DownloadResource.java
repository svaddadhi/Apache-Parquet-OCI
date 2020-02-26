package helidon.oci.library;

//import io.helidon.common.http.MediaType;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.*;

import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

//import javax.ws.rs.Produces;

@Path("/download")
@RequestScoped
public class DownloadResource {
    private final GreetingProvider greetingProvider;

    @Inject
    public DownloadResource(GreetingProvider greetingConfig) {
        this.greetingProvider = greetingConfig;
    }

    @GET
    @Path("{id}")
    public String downloadFile(@PathParam("id") int id) {
        return id + "\n";
    }


}
