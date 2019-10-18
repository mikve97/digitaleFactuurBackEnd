package nl.dfbackend.git;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import nl.dfbackend.git.health.TemplateHealthCheck;
import nl.dfbackend.git.resources.ProjectResource;

public class MainApplication extends Application<MainConfiguration> {

    public static void main(final String[] args) throws Exception {
        new MainApplication().run(args);
    }

    @Override
    public String getName() {
        return "HelloDropwizard";
    }


    @Override
    public void initialize(final Bootstrap<MainConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(MainConfiguration configuration, Environment environment) {

        final ProjectResource resource = new ProjectResource(
                configuration.getTemplate(),
                configuration.getDefaultName()
        );

        final TemplateHealthCheck healthCheck =
                new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);

        environment.jersey().register(resource);

    }

}
