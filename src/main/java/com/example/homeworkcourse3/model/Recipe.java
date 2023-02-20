package com.example.homeworkcourse3.model;

import com.example.homeworkcourse3.model.Ingredient;
import lombok.*;

import java.util.List;


@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {
    private String name;
    private int timeMin;
    private List<Ingredient> ingredients;
    private List<String> steps;

    @Override
    public String toString() {
        return name + "\n Впремя приготовления: " + timeMin;
    }
}
