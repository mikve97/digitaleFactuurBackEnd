package nl.dfbackend.git.auth;

import com.sun.javaws.JAuthenticator;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.basic.BasicCredentials;
import nl.dfbackend.git.api.User;

import java.util.Optional;

public class Authenticator implements io.dropwizard.auth.Authenticator<BasicCredentials, User> {

    @Override
    public Optional<User> authenticate(BasicCredentials basicCredentials) throws AuthenticationException {
        if ("p@ssw0rd".equals(basicCredentials.getPassword())){
            return Optional.of(new User());
        }
        else{
            return Optional.empty();
        }
    }
}
