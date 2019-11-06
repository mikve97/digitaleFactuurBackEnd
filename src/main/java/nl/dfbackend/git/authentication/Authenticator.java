package nl.dfbackend.git.authentication;

import com.sun.javaws.JAuthenticator;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.basic.BasicCredentials;
import nl.dfbackend.git.api.User;
import nl.dfbackend.git.persistences.LoginDAO;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.NumericDate;
import org.jose4j.jwt.consumer.JwtContext;
import org.skife.jdbi.v2.DBI;

import java.util.Optional;

public class Authenticator implements io.dropwizard.auth.Authenticator<JwtContext, User> {

    private LoginDAO loginDAO;

    public Authenticator(DBI dbi) {
        this.loginDAO = dbi.open(LoginDAO.class);
    }

    @Override
    public Optional<User> authenticate(JwtContext context) {
        // Provide your own implementation to lookup users based on the principal attribute in the
        // JWT Token. E.g.: lookup users from a database etc.
        // This method will be called once the token's signature has been verified

        // In case you want to verify different parts of the token you can do that here.
        // E.g.: Verifying that the provided token has not expired.

        // All JsonWebTokenExceptions will result in a 401 Unauthorized response.

        try {
            final JwtClaims claims = context.getJwtClaims();

            //Check if expiration time is not in the past
            if(NumericDate.now().isAfter(claims.getExpirationTime())) {
                return Optional.empty();
            }

            Long longId = (Long)claims.getClaimValue("id");
//                User user = loginDao.getUserById(longId.intValue());
//
//                if (user != null) {
//                    return Optional.of(user);
//                }

            return Optional.empty();
        }
        catch (MalformedClaimException e) { return Optional.empty(); }
    }
}