package com.example.homeworkcourse3.services;

import com.example.homeworkcourse3.model.Recipe;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public interface RecipeService {
    void addRecipe(Recipe recipe);
    Recipe showRecipe(int number);

    Map<Integer, Recipe> listRecipe();

    Recipe editRecipe(int id, Recipe recipe);

    boolean deleteRecipe(int id);

    File prepareRecipesTxt() throws IOException;

    boolean saveToFileRecipe(String json);

    String readFromFileRecipe();

    boolean cleanDataFileRecipe();

    File getDataFile();

    Path saveToFile(String content, Path path) throws IOException;

    boolean saveToFileTxtRecipe(String json);
}
