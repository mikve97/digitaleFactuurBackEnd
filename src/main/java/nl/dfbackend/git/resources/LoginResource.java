package nl.dfbackend.git.resources;

import java.sql.SQLException;
import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import nl.dfbackend.git.models.CredentialModel;
import nl.dfbackend.git.models.UserModel;
import nl.dfbackend.git.services.AuthService;
import nl.dfbackend.git.services.AuthenticationService;

/**
 * When navigating to the /login from the front-end. the LoginResource will be called.
 * An object from the LoginService is created here. through the constructor
 *
 * @author Ali Rezaa Ghariebiyan
 * @version 08-11-2019
 */

@Path("/login")
@Produces(MediaType.APPLICATION_JSON)
public class LoginResource {
    private AuthService service;
    private AuthenticationService authenticationService;

    public LoginResource(AuthService service) throws SQLException {
        this.service = service;
        this.authenticationService = new AuthenticationService();
    }

    /**
     * If credentials are given in JSON format from the front-end, through the given link /{username}/{password}
     * a response will be returned from the onLogin method.
     * The given parameters will be saved in the model 'Credential'.
     * This can be OK or UNAUTHORIZED.
     *
     * @author Ali Rezaa Ghariebiyan
     * @version 08-11-2019
     * @param username
     * @param password
     * @throws SQLException 
     */
    @Path("/{username}/{password}")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    public Optional<UserModel> onLogin(@PathParam("username") String username, @PathParam("password") String password) throws SQLException{
        CredentialModel credential = new CredentialModel(username, password);
        return authenticationService.authenticateUser(credential);
    }
}