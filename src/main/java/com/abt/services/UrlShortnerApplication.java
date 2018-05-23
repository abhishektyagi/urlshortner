package com.abt.services;

import com.abt.services.filters.RateLimitFilter;
import com.abt.services.health.AppHealthCheck;
import com.abt.services.provider.DistributedMapProvider;
import com.abt.services.resources.UrlShortnerResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class UrlShortnerApplication extends Application<UrlShortnerConfiguration> {

    public static void main(final String[] args) throws Exception {
        new UrlShortnerApplication().run(args);
    }

    @Override
    public String getName() {
        return "UrlShortner";
    }

    @Override
    public void initialize(final Bootstrap<UrlShortnerConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final UrlShortnerConfiguration configuration,
                    final Environment environment) {
        DistributedMapProvider mapProvider = DistributedMapProvider.getInstance();
        mapProvider.init(configuration.getHazelcastConfigFileName());
        environment.jersey().register(new RateLimitFilter(configuration.getRateLimit()));
        environment.jersey().register(new UrlShortnerResource(configuration.getHostName()));

        environment.healthChecks().register("URL-SHORTENER", new AppHealthCheck());
    }

}
