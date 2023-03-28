package com.tyche.spoonacularapi.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tyche.spoonacularapi.models.ComplexSearchResult;
import com.tyche.spoonacularapi.services.SpoonacularComplexSearchService;
//TODO(ehabe): Make this handler return the proto instead of ComplexSearchResult.
// import com.tyche.spoonacularapi.proto.DailyMealPlanProto;

@RestController
@RequiredArgsConstructor
public class SearchController {

    private final SpoonacularComplexSearchService searchService;
    @GetMapping("/search")
    public ResponseEntity<ComplexSearchResult> search(@RequestParam MultiValueMap<String, String> allParams) {
        return  ResponseEntity.ok(searchService.search(allParams));
    }
}
