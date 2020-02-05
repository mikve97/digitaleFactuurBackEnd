package nl.dfbackend.git.resources;

import java.sql.SQLException;
import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import nl.dfbackend.git.models.CredentialModel;
import nl.dfbackend.git.models.UserModel;
import nl.dfbackend.git.models.UserToBeLoggedIn;
import nl.dfbackend.git.services.AuthenticationService;
import nl.dfbackend.git.services.AuthorisationService;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
public class AuthResource {
    private AuthenticationService authenticationService;
    private AuthorisationService authorisationService;

    public AuthResource() throws SQLException {
        this.authenticationService = new AuthenticationService();
        this.authorisationService = new AuthorisationService();
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean onAuthenticateServingPage(@HeaderParam("Token") String TokenHeaderParam) throws SQLException {
    	boolean guard = false;
    	
    	if(authorisationService.decodeJWToken(TokenHeaderParam)) {
    		guard = true;
    		return guard;
    	}
    	
        return guard;
    }
    
    @Path("/login")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Optional<UserModel> onLogin(UserToBeLoggedIn user) throws SQLException {
        CredentialModel credential = new CredentialModel(user.getUsername(), user.getPassword());

        return authenticationService.authenticateUser(credential);
    }
}