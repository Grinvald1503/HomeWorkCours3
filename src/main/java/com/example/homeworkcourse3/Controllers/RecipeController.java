package com.example.homeworkcourse3.Controllers;

import com.example.homeworkcourse3.services.Ingredient;
import com.example.homeworkcourse3.services.Recipe;
import com.example.homeworkcourse3.services.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/recipe")
@Tag(name = "Рецепты", description = "Внесение, удаление и просмотр рецептов")

public class RecipeController {
    private RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping
    @Operation(summary = "Добавление рецепта")
    public void addRecipe(@RequestBody Recipe recipe) {
        recipeService.addRecipe(recipe);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Просмотр рецепта")
    public ResponseEntity getRecipeId(@PathVariable int id) {
        Recipe recipe = recipeService.showRecipe(id);
        if (ObjectUtils.isEmpty(recipe)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(recipe);
    }
    @GetMapping
    @Operation(summary = "Просмотр всех рецептов")
    public ResponseEntity getRecipe() {
        Map<Integer, Recipe> mapRecipe = recipeService.listRecipe();

        return ResponseEntity.ok(mapRecipe);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Изменение рецепта")
    public ResponseEntity<Recipe> editRecipe(@PathVariable int id, @RequestBody Recipe recipe) {
        Recipe recipe1 = recipeService.editRecipe(id, recipe);
        if (ObjectUtils.isEmpty(recipe1)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipe);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление рецепта")
    public ResponseEntity<Void> deleteRecipe(@PathVariable int id) {
        if (recipeService.deleteRecipe(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();

    }
}
