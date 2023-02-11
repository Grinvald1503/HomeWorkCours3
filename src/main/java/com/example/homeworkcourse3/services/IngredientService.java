package com.example.homeworkcourse3.services;

import java.util.Map;

public interface IngredientService {
    int addIngredient(Ingredient ingredient);
    Ingredient showIngredient(int number);


    Map<Integer, Ingredient> listIngredient();

    Ingredient editIngredient(int id, Ingredient ingredient);

    boolean deleteIngredient(int id);
}
