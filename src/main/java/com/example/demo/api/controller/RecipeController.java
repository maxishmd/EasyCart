package com.example.demo.api.controller;

import java.sql.Connection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.api.model.Recipe;
import com.example.demo.service.RecipeService;
import com.j256.ormlite.support.ConnectionSource;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    private final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping
    public ResponseEntity<Recipe> createRecipe(@RequestBody Recipe recipe) throws Exception {
        Recipe newRecipe = recipeService.addRecipe(recipe.getName(), recipe.getDescription(), recipe.getInstructions());
        return ResponseEntity.status(HttpStatus.CREATED).body(newRecipe);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Recipe getRecipe(@PathVariable("id") Integer id) {
        return recipeService.getRecipe(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Recipe not found"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recipe> updateRecipe(@PathVariable("id") Integer id, @RequestBody Recipe recipe) {
        Recipe updatedRecipe = recipeService.updateRecipe(id, recipe.getName(), recipe.getDescription(), recipe.getInstructions());
        return ResponseEntity.status(HttpStatus.OK).body(updatedRecipe);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRecipe(@PathVariable("id") Integer id) {
        int deletedRows = recipeService.deleteRecipe(id);
        if (deletedRows == 1){
            return ResponseEntity.status(HttpStatus.OK).body("Recipe deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Recipe not found");
        }
    }

    @DeleteMapping("/ingredients")
public ResponseEntity<String> deletedRecipeAndIngredient(@RequestParam String id) {
    try {
        Integer numericId = Integer.parseInt(id);
        int deletedRows = recipeService.deleteRecipeAndIngredient(numericId);
        if (deletedRows == 1) {
            return ResponseEntity.status(HttpStatus.OK).body("Recipe and Ingredient deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Recipe and Ingredient not found");
        }
    } catch (NumberFormatException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid ID format");
    }
}

}
