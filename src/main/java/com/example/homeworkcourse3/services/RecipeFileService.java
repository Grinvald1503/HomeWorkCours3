package com.example.homeworkcourse3.services;

public interface RecipeFileService {
    boolean saveToFileRecipe(String json);

    String readFromFileRecipe();
}
