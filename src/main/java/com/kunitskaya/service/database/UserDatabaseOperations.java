package com.kunitskaya.service.database;

import com.kunitskaya.entity.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

public class UserDatabaseOperations extends DatabaseOperations {

    public void createTable() {
        String query = "create table if not exists users(username VARCHAR(30), password VARCHAR(30), role VARCHAR(20), primary key (username))";
        jdbcTemplate.update(query);
    }

    public void addUser(User user) {
        String query = "INSERT INTO users VALUES(?, ?, ?)";
        jdbcTemplate.update(query, user.getUsername(), user.getPassword(), user.getRole().name());
    }

    public User getUser(String username, String password) {
        String query = "SELECT *  FROM users WHERE username = ? && password = ?";
        Object[] args = {username, password};
        return jdbcTemplate.queryForObject(query, args, new BeanPropertyRowMapper<>(User.class));
    }
}
