package com.example.homeworkcourse3.Controllers;

import com.example.homeworkcourse3.services.Ingredient;
import com.example.homeworkcourse3.services.Recipe;
import com.example.homeworkcourse3.services.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/recipe")

public class RecipeController {
    private RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping
    public void addRecipe(@RequestBody Recipe recipe) {
        recipeService.addRecipe(recipe);
    }

    @GetMapping("/{id}")
    public ResponseEntity getRecipeId(@PathVariable int id) {
        Recipe recipe = recipeService.showRecipe(id);
        if (recipe == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(recipe);
    }
    @GetMapping
    public ResponseEntity getRecipe() {
        Map<Integer, Recipe> mapRecipe = recipeService.listRecipe();

        return ResponseEntity.ok(mapRecipe);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recipe> editRecipe(@PathVariable int id, @RequestBody Recipe recipe) {
        Recipe recipe1 = recipeService.editRecipe(id, recipe);
        if (recipe1 == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipe);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable int id) {
        if (recipeService.deleteRecipe(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();

    }
}
