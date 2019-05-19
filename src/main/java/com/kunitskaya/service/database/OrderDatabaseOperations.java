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
            increaseProductCount(order, product);
        } catch (EmptyResultDataAccessException e) {
            product = new Product();
            product.setId(productId);
            String query = "insert into " + order.getId() + " values(?, ?)";
            jdbcTemplate.update(query, productId, 1);
            order.getProducts().add(product);
        }
    }

    private void increaseProductCount(Order order, Product product) {
        logger.info("Updating product count, product id: " + product.getId());

        Integer count = getProductCount(order, product);
        count++;
        String query = "update " + order.getId() + " set count = ? where productId = ?";
        Object[] args = {count, product.getId()};
        jdbcTemplate.update(query, args);
    }

    private void decreaseProductCount(Order order, String productId) {
        logger.info("Decreasing product count, product id: " + productId);

        Integer count = getProductCount(order, productId);
        count--;
        String query = "update " + order.getId() + " set count = ? where productId = ?";
        Object[] args = {count, productId};
        jdbcTemplate.update(query, args);
    }

    public Order createOrder(User user, Order order) {
        logger.info("Creating order for user: " + user.getUsername());

        if (order == null) {
            order = new Order();
        }

        String orderId = "order" + user.getUsername();
        order.setId(orderId);
        order.setStatus(OrderStatus.CREATED);

        String query = "create table if not exists " + orderId + " (productId VARCHAR(20) NOT NULL, count INTEGER NOT NULL)";
        jdbcTemplate.execute(query);
        return order;
    }

    public boolean isProductInOrder(String productId, Order order) {

        try {
            getProductCount(order, productId);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    private Integer getProductCount(Order order, Product product) {
        String query = "select count from " + order.getId() + " where productId = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{product.getId()}, Integer.class);
    }

    private Integer getProductCount(Order order, String productId) {
        String query = "select count from " + order.getId() + " where productId = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{productId}, Integer.class);
    }

    public Map<Product, Integer> getProductCount(Order order) {
        List<Product> products = order.getProducts();
        logger.info("Products in order: " + products);
        Map<Product, Integer> productCounts = new HashMap<>();

        for (Product product : products) {
            String query = "select count from " + order.getId() + " where productId = " + product.getId();
            Integer count = jdbcTemplate.queryForObject(query, Integer.class);
            productCounts.put(product, count);
        }
        return productCounts;
    }

    public void deleteOrder(Order order) {
        logger.info("Deleting order: " + order.getId());
        String query = "delete from " + order.getId();
        jdbcTemplate.execute(query);
    }

    public void deleteProductFromOrder(String productId, Order order) {
        String message = "Deleting product: %s from order: %s";
        logger.info(String.format(message, productId, order.getId()));
        if (getProductCount(order, productId) > 1) {
            logger.info("Product count > 1. Decreasing product count");
            decreaseProductCount(order, productId);
        } else {
            String query = "delete from " + order.getId() + " where productId = ?";
            jdbcTemplate.update(query, productId);
        }
    }
}
