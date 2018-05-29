package com.abt.services.core;

import com.abt.services.api.Link;
import com.abt.services.db.DAOProvider;
import com.abt.services.provider.DistributedMapProvider;
import com.abt.services.provider.managed.ExecutorServiceProvider;
import com.abt.services.task.LinkDBSaveTask;
import com.hazelcast.core.IMap;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.WebApplicationException;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;

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
    private ExecutorServiceProvider executorServiceProvider;

    private URLShortnerManager() {
        base64 = new Base64(urlSafe);
        urlMap = DistributedMapProvider.getInstance().getUrlMap();
        executorServiceProvider = ExecutorServiceProvider.getInstance();
    }

    public static URLShortnerManager getInstance() {
        return INSTANCE;
    }

    public String create(Link link) {
        String result;
        try {
            BigInteger i = BigInteger.valueOf(link.getUrl().hashCode());
            byte[] b = i.toByteArray();
            result = new String(base64.encode(b));
            result = result.replace("\r\n", "");
            link.setCode(result);
            validateLink(link);


            if (!urlMap.containsKey(result)) {
                urlMap.put(result, link.getUrl());
                DAOProvider.getInstance().getLinkDAO().save(link);
//                executorServiceProvider.submit(new LinkDBSaveTask(link));
            }
            LOGGER.info("Created url: {} for original url: {}", result, link.getUrl());
        } catch (Exception e) {
            LOGGER.error("exception: ", e);
            throw new WebApplicationException("Server Error. ", 500);
        }
        return result;
    }

    private void validateLink(Link link) {
        link.setStatus(1);
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        if (link.getExpiry() == null || now.after(link.getExpiry())) {
            calendar.add(Calendar.YEAR, 10);
            link.setExpiry(calendar.getTime());
        }
    }

    public String get(String key) throws WebApplicationException {
        String result = urlMap.get(key);
        if (result == null) {
            throw new WebApplicationException("No Redirect URL found with key: " + key, 404);
        }
        return result;
    }

}
