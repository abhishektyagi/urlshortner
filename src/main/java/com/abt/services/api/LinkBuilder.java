package com.abt.services.api;

import java.util.Date;

public class LinkBuilder {
    private int id;
    private String uuid;
    private String tenant;
    private String code;
    private String url;
    private Date expiry;
    private Date createdAt;

    public LinkBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public LinkBuilder setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public LinkBuilder setTenant(String tenant) {
        this.tenant = tenant;
        return this;
    }

    public LinkBuilder setCode(String code) {
        this.code = code;
        return this;
    }

    public LinkBuilder setUrl(String url) {
        this.url = url;
        return this;
    }

    public LinkBuilder setExpiry(Date expiry) {
        this.expiry = expiry;
        return this;
    }

    public LinkBuilder setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Link createLink() {
        return new Link(id, uuid, tenant, code, url, expiry, createdAt);
    }
}