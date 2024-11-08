package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.example.demo.EasyCartApplication;
import com.example.demo.api.model.Ingredient;
import com.example.demo.api.model.Recipe;
import com.example.demo.api.model.User;
import com.example.demo.api.repository.IngredientRepository;
import com.example.demo.api.repository.RecipeRepository;
import com.j256.ormlite.table.TableUtils;

@Service
public class IngredientService {
    private ConnectionSource connectionSource;

    private List<Ingredient> ingredientList;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    public IngredientService(ConnectionSource connectionSource) throws Exception {
        this.connectionSource = connectionSource;
        ingredientList = getAllIngredients();
    }

    public List<Ingredient> getAllIngredients() throws Exception{
        Dao<Ingredient, Integer> ingredientDao = DaoManager.createDao(connectionSource, Ingredient.class);
        return ingredientDao.queryForAll();
    }

    public Optional<Ingredient> getIngredient(int id) {
        Optional optional = Optional.empty();
        for (Ingredient ingredient : ingredientList) {
            if (ingredient.getId() == id) {
                optional = optional.of(ingredient);
            }
        }
        return optional;
    }

    public Recipe getRecipe(int id) {
        Recipe recipe = new Recipe();
        for (Ingredient ingredient : ingredientList) {
            if (ingredient.getRecipe().getId() == id) {
                recipe = ingredient.getRecipe();
            }
        }
        return recipe;
    }

    public Ingredient addIngredient (String name, String amount, Boolean vegetarian, Boolean vegan, Boolean glutenfree, Recipe recipe){
        try {Ingredient ingredient = new Ingredient();
            ingredient.setName(name);
            ingredient.setAmount(amount);
            ingredient.setVegetarian(vegetarian);
            ingredient.setVegan(vegan);
            ingredient.setGlutenFree(glutenfree);
            ingredient.setRecipe(recipe);
            ingredientList.add(ingredient);
            ingredientRepository.save(ingredient);
            return ingredient;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Ingredient updateIngredient(int id, String name, String amount, Boolean vegetarian, Boolean vegan, Boolean glutenfree, Recipe recipe) {
        try {
            Dao<Ingredient, Integer> ingredientDao = DaoManager.createDao(connectionSource, Ingredient.class);
            Ingredient ingredient = ingredientDao.queryForId(id);
            ingredient.setName(name);
            ingredient.setAmount(amount);
            ingredient.setVegetarian(vegetarian);
            ingredient.setVegan(vegan);
            ingredient.setGlutenFree(glutenfree);
            ingredient.setRecipe(recipe);

            ingredientDao.update(ingredient);
            return ingredient;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        
    }

    public int deleteIngredient(int id) {
        try{
            Dao<Ingredient, Integer> ingredientDao = DaoManager.createDao(connectionSource, Ingredient.class);
            int deletedRows = ingredientDao.deleteById(id);
            return deletedRows;
        }catch (SQLException e){
            e.printStackTrace();
            return -1;
        }
        
    }
    
}
