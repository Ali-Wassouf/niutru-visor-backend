package com.tyche.spoonacularapi.models;

import java.util.List;
import lombok.Data;

@Data
public class ComplexSearchResult {
    private List<MinimumRecipeObject> results;
    private long offset;
    private long number;
    private long totalResults;
}
