package com.example.homeworkcourse3.model;

import com.example.homeworkcourse3.model.Ingredient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor


public class Recipe {
    private String name;
    private int timeMin;
    private List<Ingredient> ingredients;
    private List<String> steps;


}
