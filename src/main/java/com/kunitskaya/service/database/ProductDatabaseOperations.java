package com.kunitskaya.service.database;

import com.kunitskaya.entity.Product;
import com.kunitskaya.entity.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

public class ProductDatabaseOperations extends DatabaseOperations {

    public ProductDatabaseOperations() {
        createTable();
    }

    @Override
    void createTable() {
        String query = "create table if not exists products(id VARCHAR(20), name VARCHAR(30), price DOUBLE, primary key (id))";
        jdbcTemplate.execute(query);
    }

    public List<Product> getProducts() {
        String query = "SELECT * FROM products";
        return jdbcTemplate.queryForList(query, null, new BeanPropertyRowMapper<>(Product.class));

    }

    public void addProductToCart(String productId, User user) {
        String query = "";
    }


}
