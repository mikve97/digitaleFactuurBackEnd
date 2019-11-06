package nl.dfbackend.git;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import nl.dfbackend.git.resources.ProjectResource;
import nl.dfbackend.git.resources.TripResource;


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
    public void run(MainConfiguration configuration, Environment environment) {
        // code to register module
        final ProjectResource resource = new ProjectResource();
        final TripResource tripResource = new TripResource();
    	
    	environment.jersey().register(resource);
	    environment.jersey().register(tripResource);
    }

}
