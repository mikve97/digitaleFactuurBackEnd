package nl.dfbackend.git.resources;

import java.sql.SQLException;
import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import nl.dfbackend.git.models.CredentialModel;
import nl.dfbackend.git.models.UserModel;
import nl.dfbackend.git.services.AuthenticationService;


@Path("/login")
@Produces(MediaType.APPLICATION_JSON)
public class AuthResource {
	private AuthenticationService authenticationService;
	
    public AuthResource() throws SQLException{
    	this.authenticationService = new AuthenticationService();
    }

    @Path("/{username}/{password}")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    public Optional<UserModel> onLogin(@PathParam("username") String username, @PathParam("password") String password) throws SQLException{
        CredentialModel credential = new CredentialModel(username, password);
        return this.authenticationService.authenticateUser(credential);
    }
}