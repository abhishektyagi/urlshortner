package com.abt.services.api;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @author Abhishek Tyagi [abhishek.tyagi@zoomcar.com]
 * @since 1.0.8
 * Part of urlshortner
 * on 23/05/18 12:30 PM.
 */
public class Result implements Serializable {
    private String url;
    private String shortenedURL;
    private Date validTill;

    public Result(String url, String shortenedURL) {
        this.url = url;
        this.shortenedURL = shortenedURL;
    }

    public String getUrl() {
        return url;
    }

    public String getShortenedURL() {
        return shortenedURL;
    }

    public Date getValidTill() {
        return validTill;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result result = (Result) o;
        return Objects.equals(url, result.url) &&
                Objects.equals(shortenedURL, result.shortenedURL) &&
                Objects.equals(validTill, result.validTill);
    }

    @Override
    public int hashCode() {

        return Objects.hash(url, shortenedURL, validTill);
    }

    @Override
    public String toString() {
        return "Result{" +
                "url='" + url + '\'' +
                ", shortenedURL='" + shortenedURL + '\'' +
                ", validTill=" + validTill +
                '}';
    }
}
