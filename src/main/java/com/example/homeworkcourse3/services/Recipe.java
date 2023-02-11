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


}
