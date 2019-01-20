package com.kunitskaya;

import com.kunitskaya.entity.Order;
import com.kunitskaya.entity.Product;
import com.kunitskaya.entity.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class DatabaseTest extends BaseTest {

    @Test
    public void addUser() {
        String username = RandomStringUtils.randomAlphabetic(1, 10);
        String password = RandomStringUtils.randomAlphanumeric(1, 7);

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        userDatabaseOperations.addUser(user);
        User userFromDB = userDatabaseOperations.getUser(username, password);

        Assert.assertEquals(username, userFromDB.getUsername());
        Assert.assertEquals(password, userFromDB.getPassword());
    }

    @Test
    public void getProducts() {
        List<Product> testProducts = addTestProducts();
        List<Product> products = productDatabaseOperations.getProducts();
        Assert.assertTrue(products.containsAll(testProducts));
    }

    public List<Product> addTestProducts() {

        List<Product> products = new ArrayList<>();
        for (int i = 0; i < RandomUtils.nextInt(3, 15); i++) {
            Product product = new Product();
            product.setId(String.valueOf(i));
            product.setName(RandomStringUtils.randomAlphabetic(1, 10));
            product.setPrice(RandomUtils.nextDouble(10, 1000));
            productDatabaseOperations.addProduct(product);

            products.add(product);
        }
        return products;
    }

    @Test
    public void addProductToOrder() {
        User user = new User();
        user.setUsername(RandomStringUtils.randomAlphabetic(1, 5));
        Order order = orderDatabaseOperations.createOrder(user);
        orderDatabaseOperations.addProduct(RandomStringUtils.randomAlphanumeric(1, 4), order);
    }
}
