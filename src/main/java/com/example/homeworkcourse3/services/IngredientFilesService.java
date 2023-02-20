package com.example.homeworkcourse3.services;

import java.io.File;

public interface IngredientFilesService {
    boolean saveToFileIngredient(String json);

    String readFromFileIngredient();

    boolean cleanDataFileIngredient();

    File getDataFile();
}
