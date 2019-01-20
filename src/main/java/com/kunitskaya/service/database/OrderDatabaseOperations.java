package com.kunitskaya.service.database;

import com.kunitskaya.entity.Order;
import com.kunitskaya.entity.User;

public class OrderDatabaseOperations extends DatabaseOperations {
//    @Override
//    void createTable() {
//        String query = "create table if not exists order" + user.getUsername() + " (productId INTEGER, totalPrice DOUBLE, status VARCHAR(20))";
//        jdbcTemplate.execute(query);
//    }

    public void addProduct(String productId, User user) {
        createTable(user);
        String query = "insert into order" + user.getUsername() + " values(?)";
        jdbcTemplate.update(query, productId);

        logger.info("Product was added to order");
    }

    private void createTable(User user) {
        String query = "create table if not exists order" + user.getUsername() + " (productId INTEGER, count INTEGER)";
        jdbcTemplate.execute(query);
    }

    public Order getProduct(String productId) {
        String query = "select * from order where productId = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{productId}, Order.class);
    }
}
