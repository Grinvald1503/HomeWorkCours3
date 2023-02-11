package com.example.homeworkcourse3.services.impl;

import com.example.homeworkcourse3.services.Recipe;
import com.example.homeworkcourse3.services.RecipeService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service

public class RecipeServiceImpl implements RecipeService {
    public final Map<Integer, Recipe> listRecipe = new HashMap<>();
    private static int counter = 0;

    @Override

    public void addRecipe(Recipe recipe) {
        counter++;
        listRecipe.put(counter, recipe);

    }

    @Override
    public Recipe showRecipe(int id) {

        Recipe recipe = listRecipe.get(id);
        if (recipe != null) {
            return recipe;
        }

        return null;
    }

    @Override
    public Map<Integer, Recipe> listRecipe() {


        return listRecipe;

    }

    @Override
    public Recipe editRecipe(int id, Recipe recipe) {
        if (listRecipe.containsKey(id)) {
            listRecipe.put(id, recipe);
            return recipe;
        }
        return null;
    }

    @Override
    public boolean deleteRecipe(int id) {
        if (listRecipe.containsKey(id)) {
            listRecipe.remove(id);
            return true;
        }
        return false;
    }
}
