package com.tyche.spoonacularapi.models;

import lombok.Data;

@Data
public class MinimumRecipeObject {

    private long id;
    private String title;
    private String image;
    private String imageType;
    private Nutrition nutrition;

}
