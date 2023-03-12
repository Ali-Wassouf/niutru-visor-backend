package com.tyche.spoonacularapi;

import java.util.List;
import lombok.Data;

@Data
public class ComplexSearchResult {
    private List<MinimumRecipeObject> results;
    private long offset;
    private long number;
    private long totalResults;
}
