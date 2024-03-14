package com.handson.backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Component
public class StartupApplicationListener {

    private static final Logger logger = LoggerFactory.getLogger(StartupApplicationListener.class);

    private final Environment environment;

    public StartupApplicationListener(Environment environment) {
        this.environment = environment;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void afterApplicationStartup() {
        String port = environment.getProperty("local.server.port");
        String hostAddress;
        try {
            hostAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            hostAddress = "localhost";
        }
        logger.info("Application started and listening on {}:{}", hostAddress, port);
    }
}