package nl.dfbackend.git;

import io.dropwizard.Application;

import io.dropwizard.setup.Environment;
import nl.dfbackend.git.auth.HelloAuthenticator;
import nl.dfbackend.git.core.User;
import nl.dfbackend.git.resources.HelloResource;
import nl.dfbackend.git.resources.ProjectResource;
import io.dropwizard.auth.basic.*;
import io.dropwizard.auth.AuthFactory;
import io.dropwizard.auth.Authenticator;


public class MainApplication extends Application<MainConfiguration> {

    public static void main(String[] args) throws Exception {
        //Gebruik deze als we een config.yml gaan gebruiken voor bv een db verbinding
    	new MainApplication().run(new String[] {"server", "config.yml"});
//        new MainApplication().run(new String[] {"server"});
    }

    @Override
    public void run(MainConfiguration configuration, Environment environment) {
        final ProjectResource resource = new ProjectResource();
        final HelloResource resourceHello = new HelloResource();
        environment.jersey().register(resource);
        environment.jersey().register(resourceHello);
        //gebruikt andere versie, zie pom
        environment.jersey().register(AuthFactory.binder(new BasicAuthFactory<>(new HelloAuthenticator(configuration.getPassword()), "SECURITY REALM", User.class)));
    }

}
