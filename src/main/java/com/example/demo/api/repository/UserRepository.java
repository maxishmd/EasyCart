package com.example.demo.api.repository;

import com.example.demo.api.model.User;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public class UserRepository {
    private final Dao<User, Integer> userDao;

    public UserRepository(ConnectionSource connectionSource) throws SQLException {
        this.userDao = DaoManager.createDao(connectionSource, User.class);
    }

    public void save(User user) throws SQLException {
        userDao.createOrUpdate(user);
    }

    public User findById(int id) throws SQLException {
        return userDao.queryForId(id);
    }

    public List<User> findAll() throws SQLException {
        return userDao.queryForAll();
    }

    public void deleteById(int id) throws SQLException {
        userDao.deleteById(id);
    }
    
}
