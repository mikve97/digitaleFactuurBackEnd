package nl.dfbackend.git.resources;

import io.dropwizard.auth.Auth;
import nl.dfbackend.git.models.ProjectModel;
import nl.dfbackend.git.models.TripModel;
import nl.dfbackend.git.services.ProjectService;
import org.eclipse.jetty.server.Authentication;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/project")
@Produces(MediaType.APPLICATION_JSON)
public class ProjectResource {

    private final String apiKey;
    private final String userId;

    public ProjectResource(String apiKey, String userId) {
        this.apiKey = apiKey;
        this.userId = userId;
    }

    @Path("/getAllProject")
    @GET
    public Response getAllProjects() {

        ProjectService pService = new ProjectService();
        List<ProjectModel> projects = pService.getProjectsFromApi(this.apiKey, this.userId);

        if(projects != null){
            return Response.ok(projects).build();
        }else{
            return Response.ok("No projects found").build();
        }

    }
}