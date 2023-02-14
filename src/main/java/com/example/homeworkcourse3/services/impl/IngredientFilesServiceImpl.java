package com.example.homeworkcourse3.services.impl;

import com.example.homeworkcourse3.services.IngredientFilesService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class IngredientFilesServiceImpl implements IngredientFilesService {
    @Value("${path.of.data.file}")
    private String dataFilePath;
    @Value("${ingredients.of.data.file}")
    private String dataFileName;

    @Override
    public boolean saveToFileIngredient(String json) {
        try {
            cleanDataFileIngredient();
            Files.writeString(Path.of(dataFilePath, dataFileName), json);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public String readFromFileIngredient() {
        try {
            return Files.readString(Path.of(dataFilePath, dataFileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean cleanDataFileIngredient() {
        try {
            Path path = Path.of(dataFilePath, dataFileName);
            Files.deleteIfExists(path);
            Files.createFile(path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }
}
