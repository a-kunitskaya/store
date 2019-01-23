package com.kunitskaya.service.database;

import com.kunitskaya.entity.Product;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductDatabaseOperations extends DatabaseOperations {

    public void createTable() {
        String query = "create table if not exists products(id INTEGER, name VARCHAR(30), price DOUBLE, primary key (id))";
        jdbcTemplate.execute(query);
    }

    public List<Product> getProducts() {
        String query = "SELECT * FROM products";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Product.class));
    }

    public void addProduct(Product product) {
        String query = "insert into products values(?, ?, ?)";
        jdbcTemplate.update(query, product.getId(), product.getName(), product.getPrice());
    }

    public Product getProduct(String productId) {
        String query = "select * from products where id = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{productId}, new BeanPropertyRowMapper<>(Product.class));
    }
}
