package com.example.demo.api.repository;

import com.example.demo.api.model.Recipe;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public class RecipeRepository {
    private final Dao<Recipe, Integer> recipeDao;

    public RecipeRepository(ConnectionSource connectionSource) throws SQLException {
        this.recipeDao = DaoManager.createDao(connectionSource, Recipe.class);
    }

    public void save(Recipe recipe) throws SQLException {
        recipeDao.createOrUpdate(recipe);
    }

    public Recipe findById(int id) throws SQLException {
        return recipeDao.queryForId(id);
    }

    public List<Recipe> findAll() throws SQLException {
        return recipeDao.queryForAll();
    }

    public void deleteById(int id) throws SQLException {
        recipeDao.deleteById(id);
    }
}
