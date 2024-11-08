package com.example.demo.api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.api.model.Ingredient;
import com.example.demo.api.model.Recipe;
import com.example.demo.api.model.User;
import com.example.demo.service.IngredientService;
import com.example.demo.service.RecipeService;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/api/ingredients")
public class IngredientController {
    private final IngredientService ingredientService;

    @Autowired
    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping
    public ResponseEntity<Ingredient> createIngredient(@RequestBody Ingredient ingredient){
        Ingredient newIngredient;
            try{
                newIngredient = ingredientService.addIngredient(ingredient.getName(), ingredient.getAmount(), ingredient.isVegetarian(), ingredient.isVegan(), ingredient.isGlutenFree(), ingredient.getRecipe());
                return ResponseEntity.status(HttpStatus.CREATED).body(newIngredient);
            }catch (Exception e){
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
    }
        
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Ingredient getIngredient(@PathVariable("id") Integer id) {
        return ingredientService.getIngredient(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingredient not found"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ingredient> updateIngredient(@PathVariable("id") Integer id, @RequestBody Ingredient ingredient) {
        Ingredient updatedIngredient = ingredientService.updateIngredient(id, ingredient.getName(), ingredient.getAmount(), ingredient.isVegetarian(), ingredient.isVegan(), ingredient.isGlutenFree(), ingredient.getRecipe());
        return ResponseEntity.status(HttpStatus.OK).body(updatedIngredient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteIngredient(@PathVariable("id") Integer id) {
        int deletedRows = ingredientService.deleteIngredient(id);
        if (deletedRows == 1){
            return ResponseEntity.status(HttpStatus.OK).body("Ingredient deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ingredient not found");
        }
    }
    
}
