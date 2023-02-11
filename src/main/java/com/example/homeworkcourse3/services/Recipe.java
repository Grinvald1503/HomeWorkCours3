package com.example.homeworkcourse3.services;

import com.example.homeworkcourse3.services.Ingredient;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data


public class Recipe {
    private String name;
    private int timeMin;
    private List<Ingredient> ingredients;
    private List<String> steps;

    public Recipe(String name, int timeMin) {
        if (name !=null) {
            this.name = name;
        }
        else {
            throw new IllegalArgumentException("название рецепта не введено");
        }
        if (timeMin > 0) {

            this.timeMin = timeMin;
        } else {
            throw new IllegalArgumentException("введено некоректное значение");
        }
    }
}
