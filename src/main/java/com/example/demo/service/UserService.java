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
import com.example.demo.api.model.User;
import com.j256.ormlite.table.TableUtils;



@Service
public class UserService {
    private ConnectionSource connectionSource;

    private List<User> userList;

    @Autowired
    public UserService(ConnectionSource connectionSource) throws Exception {
        this.connectionSource = connectionSource;
        userList = getAllUsers();
    }

    public List<User> getAllUsers() throws Exception{
        
        Dao<User, Integer> userDao = DaoManager.createDao(connectionSource, User.class);
        return userDao.queryForAll();
    }

    public Optional<User> getUser(int id) {
        Optional optional = Optional.empty();
        for (User user : userList) {
            if (user.getId() == id) {
                optional = optional.of(user);
            }
        }
        return optional;
    }

    public User addUser(int id, String name, int age){
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setAge(age);
        userList.add(user);
        return user;
    }
}
