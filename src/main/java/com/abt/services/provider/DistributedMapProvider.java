package com.abt.services.provider;

import com.hazelcast.config.ClasspathXmlConfig;
import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Abhishek Tyagi [abhishek.tyagi@zoomcar.com]
 * @since 1.0.8
 * Part of urlshortner
 * on 23/05/18 1:18 PM.
 */
public class DistributedMapProvider {
    private static final Logger LOG = LoggerFactory.getLogger(DistributedMapProvider.class);
    private static final DistributedMapProvider INSTANCE = new DistributedMapProvider();
    private HazelcastInstance hazelcastInstance;
    private IMap<String, String> urlMap;
    private IMap<String, Integer> rateLimitMap;

    private DistributedMapProvider() {
    }

    public void init(String configFileName) {
        if (configFileName != null && !configFileName.isEmpty()) {
            Config config = new ClasspathXmlConfig(configFileName);
            LOG.info("Initializing hazelcast instance via provided configuration");
            hazelcastInstance = Hazelcast.newHazelcastInstance(config);
        } else {
            LOG.info("Initializing hazelcast instance via default configuration");
            hazelcastInstance = Hazelcast.newHazelcastInstance();
        }
        LOG.info("Distributed map initialized");
    }

    public static DistributedMapProvider getInstance() {
        return INSTANCE;
    }

    public IMap<String, String> getUrlMap() {
        if (urlMap == null) {
            urlMap = hazelcastInstance.getMap("URL_MAP");
            LOG.info("AVAILABILITY MAP INITIALIZED");
        }
        return urlMap;
    }

    public IMap<String, Integer> getRateLimitMap() {
        if (rateLimitMap == null) {
            rateLimitMap = hazelcastInstance.getMap("RATE_LIMIT_MAP");
            LOG.info("RATE LIMIT MAP initialized");
        }
        return rateLimitMap;
    }
}
