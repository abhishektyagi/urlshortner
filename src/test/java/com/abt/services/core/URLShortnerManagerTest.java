package com.abt.services.core;

import com.abt.services.UrlShortnerApplication;
import com.abt.services.UrlShortnerConfiguration;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Abhishek Tyagi [abhishek.tyagi@zoomcar.com]
 * @since 1.0.8
 * Part of urlshortner
 * on 23/05/18 5:31 PM.
 */
public class URLShortnerManagerTest {
    private URLShortnerManager manager;

    @ClassRule
    public static final DropwizardAppRule<UrlShortnerConfiguration> RULE = new DropwizardAppRule<>(UrlShortnerApplication.class, ResourceHelpers.resourceFilePath("config.yml"));


    @Before
    public void setUp() throws Exception {
        manager = URLShortnerManager.getInstance();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void createSuccess() {
        String url = "https://www.zoomcar.com/bangalore";
        String result = manager.create(url);
        assertNotNull(result);
    }

    @Test
    public void get() {
        String url = "https://www.zoomcar.com/bangalore";
        String result = manager.create(url);
        assertNotNull(result);
        String state = manager.get(result);
        assertEquals(url, state);

    }
}