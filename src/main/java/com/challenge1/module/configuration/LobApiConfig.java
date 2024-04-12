package com.challenge1.module.configuration;

import com.lob.api.ApiClient;
import com.lob.api.auth.HttpBasicAuth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LobApiConfig {

    @Value("${lob.api.key}")
    private String lobApiKey;

    @Value("${lob.api.base.url}")
    private String lobApiBaseUrl;

    private static final String AUTH_MODE = "basicAuth";

    @Bean
    public ApiClient lobClientProvider() {

        ApiClient lobClient = com.lob.api.Configuration.getDefaultApiClient();
        lobClient.setBasePath(lobApiBaseUrl);
        HttpBasicAuth basicAuth = (HttpBasicAuth) lobClient.getAuthentication(AUTH_MODE);
        basicAuth.setUsername(lobApiKey);

        return lobClient;
    }
}
