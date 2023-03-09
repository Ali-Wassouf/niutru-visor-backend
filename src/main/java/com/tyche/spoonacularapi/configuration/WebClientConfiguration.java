package com.tyche.spoonacularapi.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfiguration {

    @Value("${spoonacular.url}")
    private String url;

    @Bean
    public WebClient webClient() {
        return WebClient.builder().baseUrl(url).build();
    }

    @Bean
    public ObjectMapper mapper(){
        return new ObjectMapper();
    }
}
