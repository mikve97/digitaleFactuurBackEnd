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
import nl.dfbackend.git.services.AuthorisationService;


@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
public class AuthResource {
	private AuthenticationService authenticationService;
	private AuthorisationService authorisationService;
	
    public AuthResource() throws SQLException{
    	this.authenticationService = new AuthenticationService();
    	this.authorisationService = new AuthorisationService();
    }

    @Path("/login/{username}/{password}")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Optional<UserModel> postLogin(@PathParam("username") String username, @PathParam("password") String password) throws SQLException{
        CredentialModel credential = new CredentialModel(username, password);
        return this.authenticationService.authenticateUser(credential);
    }
    
    @Path("/logout/{username}/{password}")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean putLogin(@PathParam("username") String username, @PathParam("password") String password) throws SQLException{
        CredentialModel credential = new CredentialModel(username, password);
        //moet nog de jwt uit de header halen
        return this.authorisationService.destroyJWToken(username);
    }
}