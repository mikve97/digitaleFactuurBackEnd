package nl.dfbackend.git;

import io.dropwizard.Application;

import io.dropwizard.setup.Environment;
import nl.dfbackend.git.resources.ProjectResource;


public class MainApplication extends Application<MainConfiguration> {

    public static void main(String[] args) throws Exception {
        //Gebruik deze als we een config.yml gaan gebruiken voor bv een db verbinding
        // new MainApplication().run(new String[] {"server", "config.yml"});
        new MainApplication().run(new String[] {"server"});
    }

    @Override
    public void run(MainConfiguration configuration, Environment environment) {
        // code to register module
        final ProjectResource resource = new ProjectResource();
        environment.jersey().register(resource);
    }

}
