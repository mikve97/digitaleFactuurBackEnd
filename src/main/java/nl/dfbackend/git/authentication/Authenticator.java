package nl.dfbackend.git.authentication;

import io.dropwizard.auth.Auth;
import nl.dfbackend.git.mappers.UserMapper;
import nl.dfbackend.git.models.UserModel;
import nl.dfbackend.git.persistences.UserPersistence;
import nl.dfbackend.git.util.DbConnector;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.NumericDate;
import org.jose4j.jwt.consumer.JwtContext;
import org.skife.jdbi.v2.DBI;

import java.sql.SQLException;
import java.util.Optional;

/**
 *
 * @author Ali Rezaa Ghariebiyan
 * @version 08-11-2019
 */

public class Authenticator implements io.dropwizard.auth.Authenticator<JwtContext, UserModel> {
    private DBI dbi;
    private UserPersistence loginDAO;

    @Override
    public Optional<UserModel> authenticate(JwtContext context) {
        // Provide your own implementation to lookup users based on the principal attribute in the
        // JWT Token. E.g.: lookup users from a database etc.
        // This method will be called once the token's signature has been verified

        // In case you want to verify different parts of the token you can do that here.
        // E.g.: Verifying that the provided token has not expired.

        // All JsonWebTokenExceptions will result in a 401 Unauthorized response.

//        return UserPersistence.getUserByUsername("ali@df.nl");
        DbConnector.getInstance();
        try {
            dbi = DbConnector.getDBI();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        loginDAO = dbi.open(UserPersistence.class);
//        UserModel user =  ;

//        try {
//            final JwtClaims claims = context.getJwtClaims();
//
//            //Check if expiration time is not in the past
//            if(NumericDate.now().isAfter(claims.getExpirationTime())) {
//                return Optional.empty();
//            }
//
//            return Optional.empty();
//        }
//        catch (MalformedClaimException e) { return Optional.empty(); }
        try {
            final String subject = context.getJwtClaims().getSubject();
            UserModel user = loginDAO.getUserByUsername(subject);

            if (user.getUsername().equals(subject)) {
                return Optional.of(user);
            }
            return Optional.empty();
        }
        catch (MalformedClaimException e) { return Optional.empty(); }
    }
}