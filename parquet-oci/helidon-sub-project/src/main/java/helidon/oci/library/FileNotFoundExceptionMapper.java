package helidon.oci.library;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static javax.ws.rs.core.Response.status;

@Provider
public class FileNotFoundExceptionMapper implements ExceptionMapper<FileNotFoundException> {
    @Override
    public Response toResponse(FileNotFoundException exception) {
        return status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build();
    }
}
