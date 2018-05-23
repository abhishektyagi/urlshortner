package com.abt.services.resources;

import com.abt.services.core.URLShortnerManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author Abhishek Tyagi [abhishek.tyagi@zoomcar.com]
 * @since 1.0
 * Part of urlshortner
 * on 23/05/18 12:25 PM.
 */

@Path("/")
public class UrlShortnerResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(UrlShortnerResource.class);
    private URLShortnerManager manager;
    private final String hostname;

    public UrlShortnerResource(String hostname) {
        manager = URLShortnerManager.getInstance();
        this.hostname = hostname;
    }

    @POST
    @Path("url")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createURL(@FormParam("url") String url) {
        LOGGER.info("Generate Shortened URL: {}", url);
        validateURI(url);
        String result = this.hostname + manager.create(url);
        LOGGER.info("URL generated: {}", result);
        try {
            URI uri = new URI(result);
            return Response.created(uri).build();
        } catch (URISyntaxException e) {
            LOGGER.error("Error while parsing url: {}", url, e);
            throw new WebApplicationException("Error in generating url.", 500);
        }

    }

    @GET
    @Path("/{key}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response redirect(@PathParam("key") String key) {
        String result = manager.get(key);
        return Response.seeOther(validateURI(result)).build();
    }


    private URI validateURI(String url) throws WebApplicationException {
        try {
            return new URI(url);
        } catch (URISyntaxException e) {
            LOGGER.error("Invalid URL: {}", url, e);
            throw new WebApplicationException(String.format("Invalid URL: %s. Details: %s", url, e.getMessage()), 400);
        }

    }
}
