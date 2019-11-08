package nl.dfbackend.git;

import com.github.toastshaman.dropwizard.auth.jwt.JwtAuthFilter;
import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import nl.dfbackend.git.models.UserModel;
import nl.dfbackend.git.authentication.Authenticator;
import nl.dfbackend.git.resources.LoginResource;
import nl.dfbackend.git.resources.ProjectResource;
import nl.dfbackend.git.resources.TripResource;
import nl.dfbackend.git.services.LoginService;
import nl.dfbackend.git.util.DbConnector;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.keys.HmacKey;

import java.io.UnsupportedEncodingException;
import java.security.Principal;
import nl.dfbackend.git.resources.VehicleResource;


public class MainApplication extends Application<MainConfiguration> {

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
         new MainApplication().run(new String[] {"server", "config.yml"});
    }
    
    
    /**
     * @author Oussama Fahchouch
     */
    @Override
    public String getName() {
        return "digitaleFactuurBackEnd";
    }
    
    /**
     *  @author Oussama Fahchouch
     */
    @Override
    public void initialize(final Bootstrap<MainConfiguration> bootstrap) {
    	bootstrap.addBundle(new MigrationsBundle<MainConfiguration>() {
            @Override
                public DataSourceFactory getDataSourceFactory(MainConfiguration configuration) {
                    return configuration.getDataSourceFactory();
                }
        });
    }

    @Override
    public void run(MainConfiguration configuration, Environment environment) throws UnsupportedEncodingException {
        // code to register module
        final ProjectResource resource = new ProjectResource();
        final TripResource tripResource = new TripResource();
        final VehicleResource vehicleResource = new VehicleResource();

    	environment.jersey().register(resource);
	    environment.jersey().register(tripResource);

        final byte[] key = configuration.getJwtTokenSecret();

        final JwtConsumer consumer = new JwtConsumerBuilder()
                .setAllowedClockSkewInSeconds(30) // allow some leeway in validating time based claims to account for clock skew
                .setRequireExpirationTime() // the JWT must have an expiration time
                .setRequireSubject() // the JWT must have a subject claim
                .setVerificationKey(new HmacKey(key)) // verify the signature with the public key
                .setRelaxVerificationKeyValidation() // relaxes key length requirement
                .build(); // create the JwtConsumer instance


        environment.jersey().register(new AuthDynamicFeature(
                new JwtAuthFilter.Builder<UserModel>()
                        .setJwtConsumer(consumer)
                        .setRealm("realm")
                        .setPrefix("Bearer")
                        .setAuthenticator(new Authenticator(DbConnector.getDBI()))
                        .buildAuthFilter()));

        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(Principal.class));
        environment.jersey().register(RolesAllowedDynamicFeature.class);
        // code to register module
        final LoginResource loginResource = new LoginResource(new LoginService(key));
        environment.jersey().register(resource);
        environment.jersey().register(loginResource);
	    environment.jersey().register(vehicleResource);
    }

}
