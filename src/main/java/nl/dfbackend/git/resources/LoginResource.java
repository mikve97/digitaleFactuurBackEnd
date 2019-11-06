package nl.dfbackend.git.resources;

import nl.dfbackend.git.api.Credential;
import nl.dfbackend.git.services.LoginService;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
@Produces(MediaType.APPLICATION_JSON)
public class LoginResource {
    private LoginService service;

    public LoginResource(LoginService service) {
        this.service = service;
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    public Response onLogin(@Valid @NotNull Credential credential){
        System.out.println("loginResource entered");
        return service.onLogin(credential);
    }

}