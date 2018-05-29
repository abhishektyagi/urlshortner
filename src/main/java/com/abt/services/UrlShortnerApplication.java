package com.abt.services;

import com.abt.services.db.DAOProvider;
import com.abt.services.filters.RateLimitFilter;
import com.abt.services.health.AppHealthCheck;
import com.abt.services.provider.DistributedMapProvider;
import com.abt.services.provider.managed.ExecutorServiceProvider;
import com.abt.services.resources.UrlShortnerResource;
import io.dropwizard.Application;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UrlShortnerApplication extends Application<UrlShortnerConfiguration> {
    private static final Logger LOGGER = LoggerFactory.getLogger(UrlShortnerApplication.class);

    public static void main(final String[] args) throws Exception {
        new UrlShortnerApplication().run(args);
    }

    @Override
    public String getName() {
        return "UrlShortner";
    }

    @Override
    public void initialize(final Bootstrap<UrlShortnerConfiguration> bootstrap) {
        bootstrap.addBundle(new MigrationsBundle<UrlShortnerConfiguration>() {
            @Override
            public PooledDataSourceFactory getDataSourceFactory(UrlShortnerConfiguration urlShortnerConfiguration) {
                return urlShortnerConfiguration.getDataSourceFactory();
            }
        });
    }

    @Override
    public void run(final UrlShortnerConfiguration configuration,
                    final Environment environment) {
        DistributedMapProvider mapProvider = DistributedMapProvider.getInstance();
        mapProvider.init(configuration.getHazelcastConfigFileName());
        DAOProvider daoProvider = DAOProvider.getInstance();
        daoProvider.init(environment, configuration);
        /*managed object*/
        environment.lifecycle().manage(ExecutorServiceProvider.getInstance());

        environment.jersey().register(new RateLimitFilter(configuration.getRateLimit()));
        environment.jersey().register(new UrlShortnerResource(configuration.getHostName()));

        environment.healthChecks().register("URL-SHORTENER", new AppHealthCheck());
    }

}
