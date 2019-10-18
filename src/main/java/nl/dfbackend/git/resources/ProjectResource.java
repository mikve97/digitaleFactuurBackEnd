package nl.dfbackend.git.resources;

import com.codahale.metrics.annotation.Timed;
import nl.dfbackend.git.api.Project;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicLong;
import java.util.Optional;

@Path("/project")
@Produces(MediaType.APPLICATION_JSON)
public class ProjectResource {
    private final String template;
    private final String defaultName;
    private final AtomicLong counter;

    public ProjectResource(String template, String defaultName) {
        System.out.println("ja?");
        this.template = template;
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
    }

    @GET
    @Timed
    public Project sayHello(@QueryParam("name") Optional<String> name) {
        System.out.println("ja?");
        final String value = String.format(template, name.orElse(defaultName));
        return new Project(counter.incrementAndGet(), value);
    }
}