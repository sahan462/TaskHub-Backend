package com.damitha.gateway.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Data
@Component
public class JwtConverterProperties {

    @Value("TaskHub")
    private String resourceId;
    @Value("email")
    private String principalAttribute;
}