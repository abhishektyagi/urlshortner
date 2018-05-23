package com.abt.services.health;

import com.codahale.metrics.health.HealthCheck;
import com.abt.services.provider.DistributedMapProvider;

/**
 * @author Abhishek Tyagi [abhishek.tyagi@zoomcar.com]
 * @since 1.0.8
 * Part of urlshortner
 * on 23/05/18 3:11 PM.
 */
public class AppHealthCheck extends HealthCheck {
    @Override
    protected Result check() throws Exception {
        if( DistributedMapProvider.getInstance().getRateLimitMap() !=null){
            return Result.healthy();
        }else {
            return Result.unhealthy("Unable to init Distributed map.");
        }
    }
}
