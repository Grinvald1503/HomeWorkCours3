package com.example.homeworkcourse3.services;

import java.util.Map;

public interface RecipeService {
    void addRecipe(Recipe recipe);
    Recipe showRecipe(int number);

    Map<Integer, Recipe> listRecipe();

    Recipe editRecipe(int id, Recipe recipe);

    boolean deleteRecipe(int id);
}
