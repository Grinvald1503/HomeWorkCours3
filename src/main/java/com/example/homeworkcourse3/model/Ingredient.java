package com.example.homeworkcourse3.model;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {
    private String name;
    private int quantity;
    private String unitOfMeasurement;

    @Override
    public String toString() {
        return name +" - " + quantity + " " + unitOfMeasurement;
    }
}
