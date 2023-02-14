package com.example.homeworkcourse3.Controllers;

import com.example.homeworkcourse3.model.Ingredient;
import com.example.homeworkcourse3.services.IngredientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/ingredient")
@Tag(name = "Ингредиенты", description = "Внесение, удаление и просмотр ингридиентов")

public class IngredientsController {
    private final IngredientService ingredientService;

    public IngredientsController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping
    @Operation(summary = "Добавление игредиента")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ингридиент создан"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ингридиент не создан, проверите правильно ли введены данные"
            )

    }
    )

    public ResponseEntity<Integer> addIngredient(@RequestBody Ingredient ingredient) {
        int id = ingredientService.addIngredient(ingredient);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Просмотр игредиента")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ингридиент найден"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ингридиент не найден, проверите правильно ли введены данные"
            )

    }
    )
    public ResponseEntity getIngredientId(@PathVariable int id) {
        Ingredient ingredient = ingredientService.showIngredient(id);
        if (ObjectUtils.isEmpty(ingredient)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(ingredient);
    }

    @GetMapping
    @Operation(summary = "Просмотр всех ингридиентов")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ингридиенты выведены"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "ошибка ввода запроса"
            )

    }
    )
    public ResponseEntity getIngredient() {
        Map<Integer, Ingredient> mapIngredient = ingredientService.listIngredient();

        return ResponseEntity.ok(mapIngredient);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Изменение игредиента")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ингридиент изменён"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ингридиент не найден, проверите правильно ли введены данные"
            )

    }
    )
    public ResponseEntity<Ingredient> editIngredient(@PathVariable int id, @RequestBody Ingredient ingredient) {
        Ingredient ingredient1 = ingredientService.editIngredient(id, ingredient);
        if (ObjectUtils.isEmpty(ingredient1)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredient);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление игредиента")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ингридиент удалён"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ингридиент не найден, проверите правильно ли введены данные"
            )

    }
    )
    public ResponseEntity<Void> deleteIngredient(@PathVariable int id) {
        if (ingredientService.deleteIngredient(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();

    }
}
