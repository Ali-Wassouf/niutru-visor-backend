package com.tyche.spoonacularapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tyche.spoonacularapi.proto.MealPlanProto;
import com.tyche.spoonacularapi.proto.MealPlanProto.DayMeals;
import com.tyche.spoonacularapi.proto.MealPlanProto.Meal;
import com.tyche.spoonacularapi.proto.MealPlanProto.MealPlan;
import com.tyche.spoonacularapi.proto.MealPlanProto.Nutrients;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.collect.ImmutableList;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class TestController {

    @Value("${spoonacular.api.key}")
    private String apiKey;
    private final WebClient webClient;
    private static final ImmutableList<String> DAYS = ImmutableList.of("sunday", "monday", "tuesday", "wednesday",
            "thursday", "friday", "saturday");
    private static int MEALNUMS = 3;

    @GetMapping("/test")
    public String test() {
        return convertToProto(webClient.get().uri(
                uriBuilder -> uriBuilder
                        .path("/mealplanner/generate")
                        .queryParam("apiKey", apiKey)
                        .queryParam("timeFrame", "week")
                        .queryParam("targetCalories", 2000)
                        .queryParam("diet", "vegetarian")
                        .build())
                .retrieve().bodyToMono(String.class).block()).toString();
    }

    private static MealPlan convertToProto(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode week = objectMapper.readTree(json).get("week");
            MealPlan.Builder builder = MealPlan.newBuilder();
            for (String day : DAYS) {
                JsonNode dayData = week.get(day);
                DayMeals.Builder dayMeal = DayMeals.newBuilder()
                        .setDay(day)
                        .setNutrients(Nutrients.newBuilder()
                                .setCalories(dayData.get("nutrients").get("calories").asDouble())
                                .setCarbohydrates(dayData.get("nutrients").get("protein").asDouble())
                                .setFat(dayData.get("nutrients").get("protein").asDouble())
                                .setProtien(dayData.get("nutrients").get("protein").asDouble())
                                .build());
                for (int i = 0; i < MEALNUMS; i++) {
                    dayMeal.addMeals(Meal.newBuilder()
                            .setId(dayData.get("meals").get(i).get("id").asInt())
                            .setTitle(dayData.get("meals").get(i).get("title").asText())
                            .setServings(dayData.get("meals").get(i).get("servings").asInt())
                            .setSourceUrl(dayData.get("meals").get(i).get("sourceUrl").asText()));
                }
                builder.addDayMeal(dayMeal.build());
            }
            return builder.build();
        } catch (IOException e) {
            e.printStackTrace();
            return MealPlan.getDefaultInstance();
        }
    }
}
