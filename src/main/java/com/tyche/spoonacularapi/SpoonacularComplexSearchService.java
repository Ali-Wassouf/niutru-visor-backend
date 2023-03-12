package com.tyche.spoonacularapi;

import java.net.URI;
import java.util.Map;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;

@Service
@RequiredArgsConstructor
public class SpoonacularComplexSearchService {

    @Value("${spoonacular.api.key}")
    private String apiKey;

    private final WebClient webClient;

    private final String PATH = "/recipes/complexSearch";

    public ComplexSearchResult search(MultiValueMap<String, String> criteria){
        return webClient.get().uri(
            uriBuilder -> uriBuilder
                .path(PATH)
                .queryParam("apiKey",apiKey)
                .queryParams(criteria)
                .build()
        ).retrieve().bodyToMono(ComplexSearchResult.class).block();
    }
}
