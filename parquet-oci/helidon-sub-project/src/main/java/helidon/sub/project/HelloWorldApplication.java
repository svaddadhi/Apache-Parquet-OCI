package helidon.sub.project;

import java.util.Optional;

import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

//import io.helidon.common.CollectionsHelper;


@ApplicationScoped
@ApplicationPath("/")
public class HelloWorldApplication extends App {

    public Set<Class<?>> getClasses() {
        return Set.of(HelloWorldResource.class);
    }
}