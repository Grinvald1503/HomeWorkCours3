package com.example.homeworkcourse3.services.impl;

import com.example.homeworkcourse3.services.Ingredient;
import com.example.homeworkcourse3.services.Recipe;
import com.example.homeworkcourse3.services.RecipeService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service

public class RecipeServiceImpl implements RecipeService {
    public static Map<Integer, Recipe> listRecipe = new HashMap<>();
    private static int counter = 0;

    @Override

    public void addRecipe(Recipe recipe) {
        counter++;
        listRecipe.put(counter, recipe);

    }

    @Override
    public Recipe showRecipe(int number) {
        return listRecipe.get(number);
    }
}
