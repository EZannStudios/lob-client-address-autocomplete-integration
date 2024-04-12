package com.challenge1.module.configuration;

import com.challenge1.module.utils.TestUtils;
import com.lob.api.ApiClient;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@TestConfiguration
public class TestConfig {

    @Bean
    @Primary
    public ApiClient lobTestClient() {
        ApiClient lobTestClient = TestUtils.lobTestClient();
        return lobTestClient;
    }
}
