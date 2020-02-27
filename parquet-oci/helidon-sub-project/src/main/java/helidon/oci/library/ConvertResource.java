package helidon.oci.library;

import com.oracle.bmc.audit.model.Response;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/convert")
@RequestScoped
public class ConvertResource {

    @Path("/{file}")
    public String convertFile(@PathParam("file") String file) throws Exception{
        // TODO: Add Conversion library

        return "this is the convert library";
    }
}
