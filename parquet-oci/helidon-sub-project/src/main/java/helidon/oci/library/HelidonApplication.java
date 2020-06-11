package helidon.oci.library;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Filter;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;


@ApplicationScoped
@ApplicationPath("/")
public class HelidonApplication extends Application {
    /***
     * This method adds all resource files to be executed by the Helidon server
     * @return
     */
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> set = new HashSet<>();
        set.add(UploadResource.class);
        set.add(DownloadResource.class);
        set.add(GreetResource.class);
        set.add(ConvertResource.class);
        set.add(FilterResource.class);
        set.add(FileNotFoundExceptionMapper.class);
        return Collections.unmodifiableSet(set);
    }

}
