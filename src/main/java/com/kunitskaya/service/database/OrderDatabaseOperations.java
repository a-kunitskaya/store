package com.kunitskaya.service.database;

import com.kunitskaya.entity.Order;
import com.kunitskaya.entity.OrderStatus;
import com.kunitskaya.entity.Product;
import com.kunitskaya.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDatabaseOperations extends DatabaseOperations {
    @Autowired
    private ProductDatabaseOperations productDatabaseOperations;

    public void addProduct(String productId, Order order) {
        logger.info("Adding product to order, product id: " + productId);

        Product product;
        try {
            product = productDatabaseOperations.getProduct(productId);
            updateProductCount(order, product);
        } catch (EmptyResultDataAccessException e) {
            product = new Product();
            product.setId(productId);
            String query = "insert into " + order.getId() + " values(?, ?)";
            jdbcTemplate.update(query, productId, 1);
            order.getProducts().add(product);
        }
    }

    private void updateProductCount(Order order, Product product) {
        logger.info("Updating product count, product id: " + product.getId());

        Integer count = getProductCount(order, product);
        count++;
        String query = "update " + order.getId() + " set count = ? where productId = ?";
        Object[] args = {count, product.getId()};
        jdbcTemplate.update(query, args);
    }

    public Order createOrder(User user) {
        logger.info("Creating order for user: " + user.getUsername());

        Order order = new Order();
        String orderId = "order" + user.getUsername();
        order.setId(orderId);
        order.setStatus(OrderStatus.CREATED);

        String query = "create table if not exists " + orderId + " (productId VARCHAR(20) NOT NULL, count INTEGER NOT NULL)";
        jdbcTemplate.execute(query);
        return order;
    }

    private Order getOrderProduct(String productId, Order order) {
        String query = "select * from " + order.getId() + " where productId = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{productId}, Order.class);
    }

    private Integer getProductCount(Order order, Product product) {
        String query = "select count from " + order.getId() + " where productId = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{product.getId()}, Integer.class);
    }

    public Map<Product, Integer> getProductCount(Order order) {
        List<Product> products = order.getProducts();
        Map<Product, Integer> productCounts = new HashMap<>();

        for (Product product : products) {
            String query = "select count from " + order.getId() + "where productId = " + product.getId();
            Integer count = jdbcTemplate.queryForObject(query, Integer.class);
            productCounts.put(product, count);
        }

        return productCounts;
    }
}
