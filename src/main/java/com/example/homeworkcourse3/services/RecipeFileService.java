package com.example.homeworkcourse3.services;

import java.io.File;

public interface RecipeFileService {
    boolean saveToFileRecipe(String json);

    String readFromFileRecipe();

    boolean cleanDataFileRecipe();

    File getDataFile();
}
