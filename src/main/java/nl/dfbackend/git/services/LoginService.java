package nl.dfbackend.git.services;

import nl.dfbackend.git.api.Credential;
import nl.dfbackend.git.api.User;
import nl.dfbackend.git.persistences.LoginDAO;
import nl.dfbackend.git.util.DbConnector;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.keys.HmacKey;
import org.jose4j.lang.JoseException;
import org.skife.jdbi.v2.DBI;

import javax.ws.rs.core.Response;

import static org.jose4j.jws.AlgorithmIdentifiers.HMAC_SHA256;

public class LoginService {
    private final byte[] tokenSecret;
    private DBI dbi;
    private LoginDAO loginDAO;

    public LoginService(byte[] token){
        this.tokenSecret = token;
        DbConnector.getInstance();
        this.dbi = DbConnector.getDBI();
//        this.loginDAO = DbConnector.getDBI().open(LoginDAO.class);
    }

    public Response onLogin(Credential credential) {
//        System.out.println(String.format("user tries to login: %s, %s", credential.getUsername(), credential.getPassword()));

//        loginDAO = dbi.open(LoginDAO.class);
        // controleer of gebruiker bestaat.
        // hier komt de dao waarin een query heb staan dat gebruiker controlleert of die bestaat.
        loginDAO = dbi.open(LoginDAO.class);
        User user = loginDAO.getUserByUsername(credential.getUsername());
        loginDAO.close();

//        tripDAO = dbi.open(TripPersistence.class);
//        tripDAO.createTripByUser(userId, licensePlate, startLocation, endLocation, startKilometergauge, endKilometergauge);
//        tripDAO.close();

        if(user == null) {
            //unauthorized unknown username
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        if(!user.getPassword().equals(credential.getPassword())) {
            //unauthorized password does not match
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        // maak token
        String token = generateToken(user.getUserId());
        user.setAuthToken(token);

//        loginDAO.close();
        //user ingelogd -> return jwt token
        return Response.status(Response.Status.OK).entity(user).build();
    }

    private String generateToken(int userId) {
        final JwtClaims claims = new JwtClaims();
        claims.setExpirationTimeMinutesInTheFuture(-20);
        claims.setClaim("id", userId);

        final JsonWebSignature jws = new JsonWebSignature();
        jws.setPayload(claims.toJson());
        jws.setAlgorithmHeaderValue(HMAC_SHA256);
        jws.setKey(new HmacKey(tokenSecret));

        try {
            return jws.getCompactSerialization();
        } catch (JoseException e) {
            e.printStackTrace();
        }
        return null;
    }
}