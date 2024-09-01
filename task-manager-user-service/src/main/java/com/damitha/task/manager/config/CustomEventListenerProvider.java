package com.damitha.task.manager.config;

import com.damitha.task.manager.model.User;
import org.keycloak.events.Event;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.EventType;
import org.keycloak.events.admin.AdminEvent;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.UserModel;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class CustomEventListenerProvider implements EventListenerProvider {

    private final KeycloakSession session;

    public CustomEventListenerProvider(KeycloakSession session) {
        this.session = session;
    }

    @Override
    public void onEvent(Event event) {
        if (event.getType() == EventType.REGISTER) {
            String userId = event.getUserId();
            UserModel keycloakUser = session.users().getUserById(session.getContext().getRealm(), userId);

            // Create a new User entity
            Map<String, String> user = new HashMap<>();
            user.put("username", keycloakUser.getUsername());
            user.put("email", keycloakUser.getEmail());
            user.put("firstName", keycloakUser.getFirstName());
            user.put("lastName", keycloakUser.getLastName());

            // Call Spring Boot service to save the user
            RestTemplate restTemplate = new RestTemplate();
            String url = "http://localhost:5000/api/auth/store";
            restTemplate.postForEntity(url, user, String.class);
        }
    }

    @Override
    public void onEvent(AdminEvent adminEvent, boolean b) {
        // Handle admin events if needed
    }

    @Override
    public void close() {
        // Cleanup if necessary
    }
}
