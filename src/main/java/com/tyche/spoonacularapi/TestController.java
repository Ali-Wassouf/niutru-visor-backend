package com.tyche.spoonacularapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequiredArgsConstructor
public class TestController {

    @Value("${spoonacular.api.key}")
    private String apiKey;
    private final WebClient webClient;

    @GetMapping("/test")
    public String test() {
        return webClient.get().uri(
            uriBuilder -> uriBuilder
                .path("/mealplanner/generate")
                .queryParam("apiKey", apiKey)
                .queryParam("timeFrame", "week")
                .queryParam("targetCalories", 2000)
                .queryParam("diet", "vegetarian")
                .build()).retrieve().bodyToMono(String.class).block();
    }
}
