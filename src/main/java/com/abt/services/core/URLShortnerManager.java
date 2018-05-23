package com.abt.services.core;

import com.abt.services.provider.DistributedMapProvider;
import com.hazelcast.core.IMap;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.WebApplicationException;
import java.math.BigInteger;

/**
 * @author Abhishek Tyagi [abhishek.tyagi@zoomcar.com]
 * @since 1.0.8
 * Part of urlshortner
 * on 23/05/18 12:28 PM.
 */
public class URLShortnerManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(URLShortnerManager.class);
    private static boolean urlSafe = true;
    private static final URLShortnerManager INSTANCE = new URLShortnerManager();
    private Base64 base64;
    private IMap<String, String> urlMap;

    private URLShortnerManager() {
        base64 = new Base64(urlSafe);
        urlMap = DistributedMapProvider.getInstance().getUrlMap();
    }

    public static URLShortnerManager getInstance() {
        return INSTANCE;
    }

    public String create(String url) {
        String result;
        try {
            BigInteger i = BigInteger.valueOf(url.hashCode());
            byte[] b = i.toByteArray();
            result = new String(base64.encode(b));
            result = result.replace("\r\n","");
            urlMap.put(result, url);
            LOGGER.info("Created url: {} for original url: {}", result, url);
        } catch (Exception e){
            LOGGER.error("exception: ",e);
            throw new WebApplicationException("Server Error. ",500);
        }
        return result;
    }

    public String get(String key) throws WebApplicationException{
        String result = urlMap.get(key);
        if(result == null){
            throw new WebApplicationException("No Redirect URL found with key: "+key, 404);
        }
        return result;
    }

}
