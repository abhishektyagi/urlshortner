package com.abt.services.filters;

import com.hazelcast.core.IMap;
import com.abt.services.provider.DistributedMapProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author Abhishek Tyagi [abhishek.tyagi@zoomcar.com]
 * @since 1.0.8
 * Part of urlshortner
 * on 23/05/18 1:53 PM.
 */
public class RateLimitFilter implements ContainerRequestFilter {
    @Context
    private HttpServletRequest context;
    private static final Logger LOG = LoggerFactory.getLogger(RateLimitFilter.class);
    private final int rateLimit;
    private IMap<String, Integer> rateLimitMap;

    public RateLimitFilter(int rateLimit) {
        this.rateLimit = rateLimit;
        rateLimitMap = DistributedMapProvider.getInstance().getRateLimitMap();
    }

    /**
     * @param requestContext request context.
     * @throws IOException if an I/O exception occurs.
     * @see javax.ws.rs.container.PreMatching
     */
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String ip = context.getRemoteAddr();
        LOG.info("IP: {}", ip);
        rateLimitValidation(ip);

    }

    private void rateLimitValidation(String key) {
        Integer counter = rateLimitMap.getOrDefault(key, 0);
        if (counter < rateLimit) {
            if (counter == 0) {
                rateLimitMap.put(key, 1, 1, TimeUnit.MINUTES);
            } else {
                counter++;
                rateLimitMap.replace(key, counter);
            }
        } else {
            throw new WebApplicationException("Too many Requests. You're being rate limited.", 429);
        }
    }
}
