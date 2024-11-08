package com.example.demo.api.controller;

import org.springframework.web.bind.annotation.RestController;
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
@RequestMapping("/api/messages")
public class MeesageController {
    
    @GetMapping("/hello")
    public String hello (){
        return "Hello World";
    }
}
