package nl.dfbackend.git.resources;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import nl.dfbackend.git.models.ProjectModel;
import nl.dfbackend.git.services.ProjectService;
/**
 * @author Mike van Es
 */
@Path("/project")
@Produces(MediaType.APPLICATION_JSON)
public class ProjectResource {

    private final String apiKey;
    private final String userId;
    ProjectService pService;

    public ProjectResource(String apiKey, String userId) throws SQLException {
        this.apiKey = apiKey;
        this.userId = userId;
        this.pService = new ProjectService();
    }

    /**
     * Gets all valid projects registerd at digitalefactuur, if we have them at our trip table it adds the corresponding trips.
     * @author Mike van Es
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
     * @author Mike van Es
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
     * @author Mike van Es
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