package com.kunitskaya.service.database;

import com.kunitskaya.entity.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

@Service
public class UserDatabaseOperations extends DatabaseOperations {

    public void addUser(User user) {
        String query = "INSERT INTO users VALUES(?, ?)";
        jdbcTemplate.update(query, user.getUsername(), user.getPassword());
    }

    public User getUser(String username, String password) {
        String query = "SELECT *  FROM users WHERE username = ? && password = ?";
        Object[] args = {username, password};
        return jdbcTemplate.queryForObject(query, args, new BeanPropertyRowMapper<>(User.class));
    }
}
