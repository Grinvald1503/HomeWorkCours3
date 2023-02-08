package com.example.homeworkcourse3.services;

public class Ingredient {
    private String name;
    private int quantity;
    private String UnitOfMeasurement;

    public Ingredient(String name, int quantity, String unitOfMeasurement) {
        if (name != null) {

            this.name = name;
        } else {
            throw new RuntimeException("название ингридиента не введено");
        }
        if (quantity > 0) {

            this.quantity = quantity;
        } else {
            throw new RuntimeException("введено некоректное значение");
        }
        if (unitOfMeasurement != null) {
            UnitOfMeasurement = unitOfMeasurement;
        } else {
            throw new RuntimeException(quantity + " ЧЕГО? КРОКОДИЛОВ?");
        }       //Великие фразы учителей математики
    }
}
