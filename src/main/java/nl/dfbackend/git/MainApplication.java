package nl.dfbackend.git;

import com.github.toastshaman.dropwizard.auth.jwt.JwtAuthFilter;
import io.dropwizard.Application;

import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.setup.Environment;
import nl.dfbackend.git.api.User;
import nl.dfbackend.git.persistence.LoginDAO;
import nl.dfbackend.git.resources.LoginResource;
import nl.dfbackend.git.resources.ProjectResource;
import nl.dfbackend.git.service.LoginService;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.keys.HmacKey;

import java.io.UnsupportedEncodingException;
import java.security.Principal;


public class MainApplication extends Application<MainConfiguration> {

    public static void main(String[] args) throws Exception {
        //Gebruik deze als we een config.yml gaan gebruiken voor bv een db verbinding
        // new MainApplication().run(new String[] {"server", "config.yml"});
        new MainApplication().run(new String[] {"server"});
    }

    @Override
    public void run(MainConfiguration configuration, Environment environment) throws UnsupportedEncodingException {
        final byte[] key = configuration.getJwtTokenSecret();

        final JwtConsumer consumer = new JwtConsumerBuilder()
                .setAllowedClockSkewInSeconds(30) // allow some leeway in validating time based claims to account for clock skew
                .setRequireExpirationTime() // the JWT must have an expiration time
                .setRequireSubject() // the JWT must have a subject claim
                .setVerificationKey(new HmacKey(key)) // verify the signature with the public key
                .setRelaxVerificationKeyValidation() // relaxes key length requirement
                .build(); // create the JwtConsumer instance


        environment.jersey().register(new AuthDynamicFeature(
                new JwtAuthFilter.Builder<User>()
                        .setJwtConsumer(consumer)
                        .setRealm("realm")
                        .setPrefix("Bearer")
                        //.setAuthenticator(new ExampleAuthenticator())
                        .buildAuthFilter()));

        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(Principal.class));
        environment.jersey().register(RolesAllowedDynamicFeature.class);
        // code to register module
        final ProjectResource resource = new ProjectResource();
        final LoginResource loginResource = new LoginResource(new LoginService( key, new LoginDAO()));
        environment.jersey().register(resource);
        environment.jersey().register(loginResource);
    }

}
