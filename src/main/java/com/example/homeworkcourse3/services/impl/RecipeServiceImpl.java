package com.example.homeworkcourse3.services.impl;

import com.example.homeworkcourse3.model.Ingredient;
import com.example.homeworkcourse3.model.Recipe;
import com.example.homeworkcourse3.services.RecipeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

@Service

public class RecipeServiceImpl implements RecipeService {
    private Map<Integer, Recipe> listRecipe = new HashMap<>();
    private static int counter = 0;
    final private RecipeService filesService;
    @Value("${path.to.recipe.file}")
    private String dataFilePath;
    @Value("${name.of.recipe.file}")
    private String dataFileName;
    @Value("${name.of.recipe.txt.file}")
    private String dataTxtFileName;

    public RecipeServiceImpl(RecipeService filesService) {
        this.filesService = filesService;
    }
    @PostConstruct
    private void init() {
        readFromFile();
    }

    @Override

    public void addRecipe(Recipe recipe) {
        counter++;
        listRecipe.put(counter, recipe);
        saveToFile();

    }

    @Override
    public Recipe showRecipe(int id) {

        Recipe recipe = listRecipe.get(id);
        if (!ObjectUtils.isEmpty(recipe)) {
            return recipe;
        }

        return null;
    }

    @Override
    public Map<Integer, Recipe> listRecipe() {


        return listRecipe;

    }

    @Override
    public Recipe editRecipe(int id, Recipe recipe) {
        if (listRecipe.containsKey(id)) {
            listRecipe.put(id, recipe);
            saveToFile();
            return recipe;
        }
        return null;
    }

    @Override
    public boolean deleteRecipe(int id) {
        if (listRecipe.containsKey(id)) {
            listRecipe.remove(id);
            saveToFile();
            return true;
        }
        return false;
    }



    private void saveToFile() throws RuntimeException {
        try {
            String json = new ObjectMapper().writeValueAsString(listRecipe);
            filesService.saveToFileRecipe(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private void readFromFile() {
        String json = filesService.readFromFileRecipe();
        try {
            listRecipe = new ObjectMapper().readValue(json, new TypeReference<HashMap<Integer, Recipe>>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
    @Override
    public File prepareRecipesTxt() throws IOException {
        return filesService.saveToFile(recipesToString(), Path.of(dataFilePath, dataTxtFileName)).toFile();
    }
    private String recipesToString() {
        StringBuilder sb = new StringBuilder();
        String listEl = " - ";
        for (Recipe recipe : listRecipe.values()) {
            sb.append("\n").append(recipe.toString()).append("\n");
            sb.append("\nИнгредиенты:\n");
            for (Ingredient ingredient : recipe.getIngredients()) {
                sb.append(listEl).append(ingredient.toString()).append("\n");
            }
            sb.append("\nИнструкция приготовления:\n");
            for (String step: recipe.getSteps()) {
                sb.append(listEl).append(step).append("\n");
            }
        }
        return sb.append("\n").toString();
    }
@Override
    public Path saveToFile(String content, Path path) throws IOException {
        createNewFile(path);
        return Files.writeString(path, content);
    }

    private void createNewFile(Path path) throws IOException {
        Files.deleteIfExists(path);
        Files.createFile(path);
    }
    @Override
    public boolean saveToFileTxtRecipe(String json) {
        try {
            cleanDataFileRecipe();
            Files.writeString(Path.of(dataFilePath, dataTxtFileName), json);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        }

    @Override
    public boolean saveToFileRecipe(String json) {
        try {
            cleanDataFileRecipe();
            Files.writeString(Path.of(dataFilePath, dataFileName), json);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String readFromFileRecipe() {
        try {
            return Files.readString(Path.of(dataFilePath, dataFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Ошибка чтения";
    }
    @Override
    public boolean cleanDataFileRecipe() {
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
