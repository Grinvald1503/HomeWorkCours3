package com.example.homeworkcourse3.services.impl;

import com.example.homeworkcourse3.services.Ingredient;
import com.example.homeworkcourse3.services.IngredientService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service

public class IngredientServiceImpl implements IngredientService {
    public static Map<Integer, Ingredient> listIngredients = new HashMap<>();
    private static int counter = 0;

    @Override

    public void addIngredient(Ingredient ingredient) {
        counter++;
        listIngredients.put(counter, ingredient);

    }

    @Override
    public Ingredient showIngredient(int number) {
        return listIngredients.get(number);
    }
}
