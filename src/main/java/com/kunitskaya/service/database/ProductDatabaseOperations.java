package com.kunitskaya.service.database;

import com.kunitskaya.entity.Product;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

public class ProductDatabaseOperations extends DatabaseOperations {

//    @Override
//    void createTable() {
//        String query = "create table if not exists products(id INTEGER, name VARCHAR(30), price DOUBLE, primary key (id))";
//        jdbcTemplate.execute(query);
//    }

    public List<Product> getProducts() {
        String query = "SELECT * FROM products";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Product.class));

    }

    public void addProduct(Product product) {
        String query = "insert into products values(?, ?, ?)";
        jdbcTemplate.update(query, product.getId(), product.getName(), product.getPrice());
    }

}
