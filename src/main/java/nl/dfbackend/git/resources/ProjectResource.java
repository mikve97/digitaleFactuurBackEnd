package nl.dfbackend.git.resources;

import nl.dfbackend.git.api.Project;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/project")
@Produces(MediaType.APPLICATION_JSON)
public class ProjectResource {


    public ProjectResource() {
    }

    @Path("/getProject")
    @GET
    public Response getMessage(@QueryParam("jwtToken") String jwtToken, @QueryParam("projectId") String projectId) {

        Project project = new Project(1, "TestProject", 11);
        return Response.ok(project).build();

    }
}