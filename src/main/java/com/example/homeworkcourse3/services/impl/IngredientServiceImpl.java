package com.example.homeworkcourse3.services.impl;

import com.example.homeworkcourse3.services.Ingredient;
import com.example.homeworkcourse3.services.IngredientService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service

public class IngredientServiceImpl implements IngredientService {
    public final Map<Integer, Ingredient> listIngredients = new HashMap<>();
    private static int counter = 0;

    @Override

    public int addIngredient(Ingredient ingredient) {
        counter++;
        listIngredients.put(counter, ingredient);

        return counter;
    }

    @Override
    public Ingredient showIngredient(int id) {
        Ingredient ingredient = listIngredients.get(id);
        if (!ObjectUtils.isEmpty(ingredient)) {
            return ingredient;
        }

        return null;

    }
    @Override
    public Map<Integer, Ingredient> listIngredient() {


        return listIngredients;

    }

    @Override
    public Ingredient editIngredient(int id, Ingredient ingredient) {
        if (listIngredients.containsKey(id)) {
            listIngredients.put(id, ingredient);
            return ingredient;
        }
        return null;
    }
    @Override
    public boolean deleteIngredient(int id) {
        if (listIngredients.containsKey(id)) {
            listIngredients.remove(id);
            return true;
        }
        return false;
    }
}
