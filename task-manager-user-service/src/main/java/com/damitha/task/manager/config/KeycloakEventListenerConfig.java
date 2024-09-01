package com.damitha.task.manager.config;

import org.keycloak.models.KeycloakSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class KeycloakEventListenerConfig {

    @Bean
    public CustomEventListenerProvider customEventListenerProvider(KeycloakSession session) {
        return new CustomEventListenerProvider(session);
    }
}
