package nl.dfbackend.git.resources;

import nl.dfbackend.git.models.CredentialModel;
import nl.dfbackend.git.services.LoginService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
    private LoginService service;

    public LoginResource(LoginService service) {
        this.service = service;
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
     */
    @Path("/{username}/{password}")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    public Response onLogin(@PathParam("username") String username, @PathParam("password") String password){
        CredentialModel credential = new CredentialModel(username, password);
        return service.onLogin(credential);
    }
}