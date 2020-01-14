package nl.dfbackend.git;

import com.github.toastshaman.dropwizard.auth.jwt.JwtAuthFilter;
import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.oauth.OAuthCredentialAuthFilter;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import nl.dfbackend.git.models.UserModel;
import nl.dfbackend.git.authentication.Authenticator;
import nl.dfbackend.git.resources.LoginResource;
import nl.dfbackend.git.resources.ProjectResource;
import nl.dfbackend.git.resources.TripResource;
import nl.dfbackend.git.services.AuthService;
import nl.dfbackend.git.services.AuthenticationService;
import nl.dfbackend.git.util.DbConnector;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.keys.HmacKey;

import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.sql.SQLException;
import nl.dfbackend.git.resources.VehicleResource;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;

import java.util.EnumSet;


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
    public void run(MainConfiguration configuration, Environment environment) throws UnsupportedEncodingException, SQLException {
    	final FilterRegistration.Dynamic cors = environment.servlets().addFilter("CORS", CrossOriginFilter.class);

	    // Configure CORS parameters
	    cors.setInitParameter("allowedOrigins", "*");
	    cors.setInitParameter("allowedHeaders", "*");
	    cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");

	    // Add URL mapping
	    cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
	    
        final ProjectResource resource = new ProjectResource(configuration.getApiKey(), configuration.getUserId());
        final TripResource tripResource = new TripResource();
        final VehicleResource vehicleResource = new VehicleResource();
        
    	environment.jersey().register(resource);
	    environment.jersey().register(tripResource);

        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(Principal.class));
        environment.jersey().register(RolesAllowedDynamicFeature.class);
        // code to register module
        environment.jersey().register(resource);
	    environment.jersey().register(vehicleResource);
	    
	    //toevoegen van de OAuth2 authenticator
	    environment.jersey().register(new AuthDynamicFeature(
	    		new OAuthCredentialAuthFilter.Builder<UserModel>()
	    		.setAuthenticator(new AuthenticationService())
	    		.buildAuthFilter()
		));
    }

}
