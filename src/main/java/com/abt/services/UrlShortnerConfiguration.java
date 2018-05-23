package com.abt.services;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.*;

import javax.validation.constraints.*;

public class UrlShortnerConfiguration extends Configuration {
    private String hazelcastConfigFileName;
    private int rateLimit;
    private String hostName;

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getHazelcastConfigFileName() {
        return hazelcastConfigFileName;
    }

    public void setHazelcastConfigFileName(String hazelcastConfigFileName) {
        this.hazelcastConfigFileName = hazelcastConfigFileName;
    }


    public int getRateLimit() {
        return rateLimit;
    }

    public void setRateLimit(int rateLimit) {
        this.rateLimit = rateLimit;
    }
}
