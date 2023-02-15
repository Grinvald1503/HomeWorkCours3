package com.example.homeworkcourse3.services;

public interface IngredientFilesService {
    boolean saveToFileIngredient(String json);

    String readFromFileIngredient();
}
