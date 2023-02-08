package com.example.homeworkcourse3.Controllers;

import com.example.homeworkcourse3.services.Ingredient;
import com.example.homeworkcourse3.services.IngredientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ingr")

public class IngredientsController {
   private IngredientService ingredientService;

    public IngredientsController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }
    @GetMapping("/add")
    public void addIngredient(@RequestParam Ingredient recipe) {
        ingredientService.addIngredient(recipe);
    }

    @GetMapping("/get")
    public Ingredient getIngredient(@RequestParam int number) {
        return ingredientService.showIngredient(number);
    }
}
