package helidon.oci.library;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;


@ApplicationScoped
@ApplicationPath("/")
public class HelidonApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> set = new HashSet<>();
        set.add(UploadResource.class);
        set.add(DownloadResource.class);
        set.add(GreetResource.class);
        set.add(ConvertResource.class);
        set.add(FileNotFoundExceptionMapper.class);
        return Collections.unmodifiableSet(set);
    }

}
