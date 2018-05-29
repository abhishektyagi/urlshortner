package com.abt.services.provider.managed;

import io.dropwizard.lifecycle.Managed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Abhishek Tyagi [abhishek.tyagi@zoomcar.com]
 * @since 1.0.8
 * Part of urlshortner
 * on 29/05/18 8:10 PM.
 */
public class ExecutorServiceProvider implements Managed {
    private final Logger LOGGER = LoggerFactory.getLogger(ExecutorServiceProvider.class);
    private static final ExecutorServiceProvider INSTANCE = new ExecutorServiceProvider();
    private static ExecutorService executorService;

    private ExecutorServiceProvider() {
    }

    public static ExecutorServiceProvider getInstance() {
        return INSTANCE;
    }

    @Override
    public void start() throws Exception {
        LOGGER.info("started");
        executorService = Executors.newCachedThreadPool();
    }

    @Override
    public void stop() throws Exception {
        LOGGER.info("stopped");
        executorService.shutdown();
    }

    public static ExecutorService getExecutorService() {
        return executorService;
    }

    public void submit(Runnable task) {
        executorService.submit(task);
    }
}