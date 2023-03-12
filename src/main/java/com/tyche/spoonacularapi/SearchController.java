package com.tyche.spoonacularapi;

import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SearchController {

    private final SpoonacularComplexSearchService searchService;
    @GetMapping("/search")
    public ResponseEntity<ComplexSearchResult> search(@RequestParam MultiValueMap<String, String> allParams) {
        return  ResponseEntity.ok(searchService.search(allParams));
    }
}
