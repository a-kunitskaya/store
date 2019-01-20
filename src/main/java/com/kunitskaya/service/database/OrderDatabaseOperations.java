package com.kunitskaya.service.database;

import com.kunitskaya.entity.User;

public class OrderDatabaseOperations extends DatabaseOperations {
//    @Override
//    void createTable() {
//        String query = "create table if not exists order" + user.getUsername() + " (productId INTEGER, totalPrice DOUBLE, status VARCHAR(20))";
//        jdbcTemplate.execute(query);
//    }

    public void addProduct(String productId, User user) {
        createTable();
        String query = "insert into order" + user.getUsername() + " values(?, ?, ?)";
        jdbcTemplate.update(query);
    }

    private void createTable() {
        String query = "create table if not exists order" + user.getUsername() + " (productId INTEGER, totalPrice DOUBLE, status VARCHAR(20))";
        jdbcTemplate.execute(query);
    }

    //if user = nullpointer, pass user in args
}
