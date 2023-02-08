package com.example.homeworkcourse3.Controllers;

import com.example.homeworkcourse3.services.Recipe;
import com.example.homeworkcourse3.services.RecipeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Recipe")

public class RecipeController {
    private RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/add")
    public void addRecipe(@RequestParam Recipe recipe) {
        recipeService.addRecipe(recipe);
    }

    @GetMapping("/get")
    public Recipe getRecipe(@RequestParam int number) {
        return recipeService.showRecipe(number);
    }
}
