package nl.dfbackend.git.resources;

import nl.dfbackend.git.api.Credential;
import nl.dfbackend.git.service.LoginService;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.keys.HmacKey;
import org.jose4j.lang.JoseException;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import static org.jose4j.jws.AlgorithmIdentifiers.HMAC_SHA256;
@Path("/login")
@Produces(MediaType.APPLICATION_JSON)
public class LoginResource {
    private LoginService service;

    public LoginResource(LoginService service) {
        this.service = service;
    }

    @POST
    public Response onLogin(@Valid @NotNull Credential credentail){
        return service.onLogin(credentail);
    }

}
