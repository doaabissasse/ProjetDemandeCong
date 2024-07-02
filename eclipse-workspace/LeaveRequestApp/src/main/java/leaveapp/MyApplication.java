package leaveapp;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/api")
public class MyApplication extends ResourceConfig {
    public MyApplication() {
        register(salarierResource.class);
        register(CORSFilter.class);
    }
}
