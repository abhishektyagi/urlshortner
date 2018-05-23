package com.abt.services;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;

public class UrlShortnerConfiguration extends Configuration {
    private String hazelcastConfigFileName;
    private int rateLimit;

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
