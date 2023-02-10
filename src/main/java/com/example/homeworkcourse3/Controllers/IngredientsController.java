package com.example.homeworkcourse3.Controllers;

import com.example.homeworkcourse3.services.Ingredient;
import com.example.homeworkcourse3.services.IngredientService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ingr")

public class IngredientsController {
   private final IngredientService ingredientService;

    public IngredientsController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }
    @PostMapping("/new")
    public void addIngredient(@RequestBody Ingredient recipe) {
        ingredientService.addIngredient(recipe);
    }

    @GetMapping("/info/{id}")
    public Ingredient getIngredient(@PathVariable int id) {
        return ingredientService.showIngredient(id);
    }
}
