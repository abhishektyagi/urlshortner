package com.abt.services.api;

import javax.ws.rs.FormParam;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @author Abhishek Tyagi [abhishek.tyagi@zoomcar.com]
 * @since 1.0.8
 * Part of urlshortner
 * on 29/05/18 7:01 PM.
 */
public class Link implements Serializable {
    private int id;
    private String uuid;
    @FormParam("tenant")
    private String tenant;
    private String code;
    @FormParam("url")
    private String url;
    @FormParam("expiry")
    private Date expiry;
    private Integer status;
    private Date createdAt;


    public Link() {
    }

    public Link(int id, String uuid, String tenant, String code, String url, Date expiry, Date createdAt) {
        this.id = id;
        this.uuid = uuid;
        this.tenant = tenant;
        this.code = code;
        this.url = url;
        this.expiry = expiry;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getExpiry() {
        return expiry;
    }

    public void setExpiry(Date expiry) {
        this.expiry = expiry;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Link link = (Link) o;
        return id == link.id &&
                Objects.equals(uuid, link.uuid) &&
                Objects.equals(tenant, link.tenant) &&
                Objects.equals(code, link.code) &&
                Objects.equals(url, link.url) &&
                Objects.equals(expiry, link.expiry) &&
                Objects.equals(status, link.status) &&
                Objects.equals(createdAt, link.createdAt);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, uuid, tenant, code, url, expiry, status, createdAt);
    }

    @Override
    public String toString() {
        return "Link{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", tenant='" + tenant + '\'' +
                ", code='" + code + '\'' +
                ", url='" + url + '\'' +
                ", expiry=" + expiry +
                ", status=" + status +
                ", createdAt=" + createdAt +
                '}';
    }
}
