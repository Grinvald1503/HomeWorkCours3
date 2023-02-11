package com.example.homeworkcourse3.Controllers;

import com.example.homeworkcourse3.services.Ingredient;
import com.example.homeworkcourse3.services.IngredientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/ingredient")

public class IngredientsController {
    private final IngredientService ingredientService;

    public IngredientsController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping
    public ResponseEntity<Integer> addIngredient(@RequestBody Ingredient ingredient) {
        int id = ingredientService.addIngredient(ingredient);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity getIngredientId(@PathVariable int id) {
        Ingredient ingredient = ingredientService.showIngredient(id);
        if (ingredient == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(ingredient);
    }
    @GetMapping
    public ResponseEntity getIngredient() {
        Map<Integer, Ingredient> mapIngredient = ingredientService.listIngredient();

        return ResponseEntity.ok(mapIngredient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ingredient> editIngredient(@PathVariable int id, @RequestBody Ingredient ingredient) {
        Ingredient ingredient1 = ingredientService.editIngredient(id, ingredient);
        if (ingredient1 == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngredient(@PathVariable int id) {
        if (ingredientService.deleteIngredient(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();

    }
}
