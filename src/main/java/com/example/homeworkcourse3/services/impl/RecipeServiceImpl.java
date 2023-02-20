package com.example.homeworkcourse3.services.impl;

import com.example.homeworkcourse3.model.Recipe;
import com.example.homeworkcourse3.services.RecipeFileService;
import com.example.homeworkcourse3.services.RecipeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service

public class RecipeServiceImpl implements RecipeService {
    private Map<Integer, Recipe> listRecipe = new HashMap<>();
    private static int counter = 0;
    final private RecipeFileService filesService;

    public RecipeServiceImpl(RecipeFileService filesService) {
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

}
