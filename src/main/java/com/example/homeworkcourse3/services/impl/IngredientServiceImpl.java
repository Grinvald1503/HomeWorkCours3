package com.example.homeworkcourse3.services.impl;

import com.example.homeworkcourse3.services.IngredientFilesService;
import com.example.homeworkcourse3.model.Ingredient;
import com.example.homeworkcourse3.services.IngredientService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service

public class IngredientServiceImpl implements IngredientService {
    final private IngredientFilesService filesService;
    private Map<Integer, Ingredient> listIngredients = new HashMap<>();
    private static int counter = 0;

    public IngredientServiceImpl(IngredientFilesService filesService) {
        this.filesService = filesService;
    }
//       @PostConstruct
//    private void init() {
//        readFromFile();
//    }

    @Override

    public int addIngredient(Ingredient ingredient) {
        counter++;
        listIngredients.put(counter, ingredient);
        saveToFile();

        return counter;
    }

    @Override
    public Ingredient showIngredient(int id) {
        Ingredient ingredient = listIngredients.get(id);
        if (!ObjectUtils.isEmpty(ingredient)) {
            return ingredient;
        }

        return null;

    }

    @Override
    public Map<Integer, Ingredient> listIngredient() {


        return listIngredients;

    }

    @Override
    public Ingredient editIngredient(int id, Ingredient ingredient) {
        if (listIngredients.containsKey(id)) {
            listIngredients.put(id, ingredient);
            saveToFile();
            return ingredient;
        }
        return null;
    }

    @Override
    public boolean deleteIngredient(int id) {
        if (listIngredients.containsKey(id)) {
            listIngredients.remove(id);
            saveToFile();
            return true;
        }
        return false;
    }

    private void saveToFile() throws RuntimeException {
        try {
            String json = new ObjectMapper().writeValueAsString(listIngredients);
            filesService.saveToFileIngredient(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private void readFromFile() {
        String json = filesService.readFromFileIngredient();
        try {
            listIngredients = new ObjectMapper().readValue(json, new TypeReference<HashMap<Integer, Ingredient>>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
