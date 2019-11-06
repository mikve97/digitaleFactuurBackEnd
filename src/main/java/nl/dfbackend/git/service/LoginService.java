package nl.dfbackend.git.service;

import nl.dfbackend.git.api.Credential;
import nl.dfbackend.git.persistence.LoginDAO;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.keys.HmacKey;
import org.jose4j.lang.JoseException;

import javax.ws.rs.core.Response;

import static org.jose4j.jws.AlgorithmIdentifiers.HMAC_SHA256;

public class LoginService {
    private final byte[] tokenSecret;

    private LoginDAO dao;

    public LoginService(byte[] token, LoginDAO dao){
        this.tokenSecret = token;
        this.dao = dao;
    }
    public Response onLogin(Credential credentail) {
        System.out.println(String.format("user tries to login: %s, %s", credentail.getUsername(), credentail.getPassword()));

        // controleer of gebruiker bestaat.
        // hier komt je dao waarin een query heb staan dat gebruiker controller of die bestaat.
        if(dao.userExist(credentail.getUsername(), credentail.getPassword()))
        {
            // todo
        }
        // maak token
        String token = generateToken();

        // if token .... niet null
        // dan versturen.
        // via @Auth kan gebruiker achterhaald worden..
        return Response.status(Response.Status.UNAUTHORIZED).build(); // geeft aan dat je verkeerde login hebt.
    }
    private String generateToken() {
        final JwtClaims claims = new JwtClaims();
        claims.setExpirationTimeMinutesInTheFuture(-20);
        claims.setSubject("good-guy");

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
