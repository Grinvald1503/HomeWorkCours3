package com.example.homeworkcourse3.services;

import lombok.Data;

@Data
public class Ingredient {
    private String name;
    private int quantity;
    private String unitOfMeasurement;

    public Ingredient(String name, int quantity, String unitOfMeasurement) {
        if (name != null) {

            this.name = name;
        } else {
            throw new RuntimeException("название ингридиента не введено");
        }
        if (quantity > 0) {

            this.quantity = quantity;
        } else {
            throw new IllegalArgumentException("введено некоректное значение");
        }
        if (unitOfMeasurement != null) {
            this.unitOfMeasurement = unitOfMeasurement;
        } else {
            throw new IllegalArgumentException(quantity + " ЧЕГО? КРОКОДИЛОВ?");
        }       //Великие фразы учителей математики
    }
}
