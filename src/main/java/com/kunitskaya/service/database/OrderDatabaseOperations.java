package com.kunitskaya.service.database;

import com.kunitskaya.entity.Order;
import com.kunitskaya.entity.Product;
import com.kunitskaya.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderDatabaseOperations extends DatabaseOperations {
    @Autowired
    private ProductDatabaseOperations productDatabaseOperations;

    public void addProduct(String productId, Order order) {
        logger.info("Adding product to order, product id: " + productId);

        Product product = productDatabaseOperations.getProduct(productId);
        if (getProduct(productId, order) == null) {
            String query = "insert into " + order.getId() + " values(?, ?)";
            jdbcTemplate.update(query, productId);
            order.getProducts().add(product);
        } else {
            updateProductCount(order, product);
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

        String query = "create table if not exists " + orderId + " (productId INTEGER NOT NULL, count INTEGER NOT NULL)";
        jdbcTemplate.execute(query);
        return order;
    }

    private Order getProduct(String productId, Order order) {
        String query = "select * from " + order.getId() + " where productId = ?";
        return jdbcTemplate.queryForObject(query, new Integer[]{Integer.parseInt(productId)}, Order.class);
    }

    private Integer getProductCount(Order order, Product product) {
        String query = "select 'count' from " + order.getId() + " where productId = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{product.getId()}, Integer.class);
    }
}
