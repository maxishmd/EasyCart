package com.example.demo.api.repository;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.api.model.Ingredient;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;

@Repository
public class IngredientRepository {
    private final Dao<Ingredient, Integer> ingredientDao;

    public IngredientRepository(ConnectionSource connectionSource) throws SQLException {
        this.ingredientDao = DaoManager.createDao(connectionSource, Ingredient.class);
    }

    public void save(Ingredient ingredient) throws SQLException {
        ingredientDao.createOrUpdate(ingredient);
    }

    public Ingredient findById(int id) throws SQLException {
        return ingredientDao.queryForId(id);
    }

    public List<Ingredient> findAll() throws SQLException {
        return ingredientDao.queryForAll();
    }

    public void deleteById(int id) throws SQLException {
        ingredientDao.deleteById(id);
    }
    
}
