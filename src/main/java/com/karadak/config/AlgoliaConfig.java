package com.karadak.config;

import com.algolia.search.APIClient;
import com.algolia.search.ApacheAPIClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AlgoliaConfig {

    @Value("${applicationId:foo}")
    private String applicationId;

    @Value("${apiKey:foe}")
    private String apiKey;

    @Bean
    public APIClient getAPIClient() {
        return new ApacheAPIClientBuilder(applicationId, apiKey).build();
    }
}
