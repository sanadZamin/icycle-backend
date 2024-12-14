package com.joprog.icycle.config;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "keycloak")
public class KeyCloakConfigData {
    private String authServerUrl;
    private String realm;
    private String resource;

    @PostConstruct
    public void init(){
        String s = "sa";
    }
}
