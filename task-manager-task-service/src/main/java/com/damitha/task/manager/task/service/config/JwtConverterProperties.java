package com.damitha.task.manager.task.service.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
//@ConfigurationProperties(prefix = "jwt.auth.converter")
public class JwtConverterProperties {

    @Value("TaskHub")
    private String resourceId;
    @Value("principal_username")
    private String principalAttribute;
}