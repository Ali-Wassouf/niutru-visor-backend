package com.tyche.spoonacularapi.models;

import java.util.List;
import lombok.Data;

@Data
public class Nutrition {

    private List<Nutrient> nutrients;
}
