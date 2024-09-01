package com.damitha.task.manager.config;

import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.EventListenerProviderFactory;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.Config;

public class CustomEventListenerProviderFactory implements EventListenerProviderFactory {

    @Override
    public EventListenerProvider create(KeycloakSession session) {
        return new CustomEventListenerProvider(session);
    }

    @Override
    public void init(org.keycloak.Config.Scope config) {
        // Initialization logic if necessary
    }

    @Override
    public void postInit(KeycloakSessionFactory factory) {
        // Post initialization logic if necessary
    }

    @Override
    public void close() {
        // Cleanup if necessary
    }

    @Override
    public String getId() {
        return "custom-event-listener";
    }
}
