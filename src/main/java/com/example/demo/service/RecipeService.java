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
import com.example.demo.api.repository.RecipeRepository;
import com.j256.ormlite.table.TableUtils;

@Service
public class RecipeService {
    private ConnectionSource connectionSource;

    private List<Recipe> recipeList;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    public RecipeService(ConnectionSource connectionSource) throws Exception {
        this.connectionSource = connectionSource;
        recipeList = getAllRecipes();
    }

    public List<Recipe> getAllRecipes() throws Exception{
        
        Dao<Recipe, Integer> recipeDao = DaoManager.createDao(connectionSource, Recipe.class);
        return recipeDao.queryForAll();
    }

    public Optional<Recipe> getRecipe(int id) {
        Optional optional = Optional.empty();
        for (Recipe recipe : recipeList) {
            if (recipe.getId() == id) {
                optional = optional.of(recipe);
            }
        }
        return optional;
    }

    public Recipe addRecipe (String name, String description, String instructions)throws Exception{
        Recipe recipe = new Recipe();
        recipe.setName(name);
        recipe.setDescription(description);
        recipeList.add(recipe);
        recipeRepository.save(recipe);
        return recipe;
    }

    public Recipe updateRecipe(int id, String name, String description, String instructions) {
        Recipe recipe = new Recipe();
        for (Recipe r : recipeList) {
            if (r.getId() == id) {
                r.setName(name);
                r.setDescription(description);
                r.setInstructions(instructions);
                recipe = r;
            }
        }
        try {recipeRepository.save(recipe);}
        catch (Exception e) {
            Logger.getLogger(EasyCartApplication.class.getName()).log(Level.SEVERE, null, e);
        }
        return recipe;
    }

    public int deleteRecipe(int id) {
        try{
            Dao<Recipe, Integer> recipeDao = DaoManager.createDao(connectionSource, Recipe.class);
            int deletedRows = recipeDao.deleteById(id);
            return deletedRows;
        }catch (SQLException e){
            Logger.getLogger(EasyCartApplication.class.getName()).log(Level.SEVERE, null, e);
            return -1;
        }
        
    }

    public int deleteRecipeAndIngredient(int id) {
        try{
            Dao<Recipe, Integer> recipeDao = DaoManager.createDao(connectionSource, Recipe.class);
            Dao<Ingredient, Integer> ingredientDao = DaoManager.createDao(connectionSource, Ingredient.class);
            List<Ingredient> ingredients = ingredientDao.queryForEq("recipeId", id);
            for (Ingredient ingredient : ingredients) {
                ingredientDao.deleteById(ingredient.getId());
            }
            int deletedRows = recipeDao.deleteById(id);
            return deletedRows;
        }catch (SQLException e){
            Logger.getLogger(EasyCartApplication.class.getName()).log(Level.SEVERE, null, e);
            return -1;
        }
    }
}