package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

@SpringBootApplication
public class EasyCartApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(EasyCartApplication.class, args);
		
	}


	@Bean
	public ConnectionSource connectionSource() throws Exception{
		return new JdbcConnectionSource("jdbc:mariadb://localhost:3306/EasyCart", "root", "123");
	}

}
