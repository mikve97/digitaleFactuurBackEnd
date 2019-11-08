package nl.dfbackend.git.resources;

import io.dropwizard.auth.Auth;
import nl.dfbackend.git.models.ProjectModel;
import nl.dfbackend.git.models.TripModel;
import nl.dfbackend.git.services.ProjectService;
import org.eclipse.jetty.server.Authentication;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/project")
@Produces(MediaType.APPLICATION_JSON)
public class ProjectResource {

    private final String apiKey;
    private final String userId;
    ProjectService pService = new ProjectService();

    public ProjectResource(String apiKey, String userId) {
        this.apiKey = apiKey;
        this.userId = userId;
    }

    /**
     * Gets all valid projects registerd at digitalefactuur, if we have them at our trip table it adds the corresponding trips.
     * @return Response
     */
    @Path("/getAllProject")
    @GET
    public Response getAllProjects() {


        List<ProjectModel> projects = this.pService.getProjectsFromApi(this.apiKey, this.userId);

        if(projects != null){
            return Response.ok(projects).build();
        }else{
            return Response.ok("No projects found").build();
        }

    }

    /**
     * Set a projectmodel in the service layer
     * @param project
     */
    @Path("/setProject")
    @POST
    public void setProject(@QueryParam("project") String project) {
        System.out.println(project);
        this.pService.setJsonProject(project);
    }

    /**
     * Get method to return the projectmodel if we have any. Returns the projectmodel in JSON format
     * @return Response
     */
    @Path("/getProject")
    @GET
    public Response getProject() {
        String jsonProject = pService.getJsonProject();

        if(jsonProject == ""){
            return Response.ok(null).build();
        }else{
            return Response.ok(jsonProject).build();
        }


    }
}