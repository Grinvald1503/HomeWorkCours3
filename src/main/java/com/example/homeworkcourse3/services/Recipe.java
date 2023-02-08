package com.example.homeworkcourse3.services;

import com.example.homeworkcourse3.services.Ingredient;

import java.util.ArrayList;

public class Recipe {
    private String name;
    private int timeMin;
    private ArrayList<Ingredient> ingredients;
    private ArrayList<String> steps;

    public Recipe(String name, int timeMin) {
        if (name !=null) {
            this.name = name;
        }
        else {
            throw new RuntimeException("название рецепта не введено");
        }
        if (timeMin > 0) {

            this.timeMin = timeMin;
        } else {
            throw new RuntimeException("введено некоректное значение");
        }
    }
}
