package com.example.homeworkcourse3.services.impl;

import com.example.homeworkcourse3.services.IngredientFilesService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class IngredientFilesServiceImpl implements IngredientFilesService {
    @Value("${path.to.ingredients.file}")
    private String dataFilePath;
    @Value("${name.of.ingredients.file}")
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
            e.printStackTrace();
        }
        return "Ошибка чтения";
    }
@Override
    public boolean cleanDataFileIngredient() {
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
@Override
    public File getDataFile() {
        return new File(dataFilePath + "/" + dataFileName);
    }
}
