package com.kunitskaya;

import com.kunitskaya.entity.Order;
import com.kunitskaya.entity.Product;
import com.kunitskaya.entity.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class DatabaseTest extends BaseTest {
    @Autowired
    private User user;
    private Order order;
    private String productId;

//    @BeforeClass
//    public void setUp() {
//        user.setUsername(RandomStringUtils.randomAlphabetic(1, 5));
//        productId = RandomStringUtils.randomAlphanumeric(1, 4);
//    }

    @Ignore
    @Test
    public void addUser() {
        String username = RandomStringUtils.randomAlphabetic(1, 10);
        String password = RandomStringUtils.randomAlphanumeric(1, 7);

        user.setUsername(username);
        user.setPassword(password);

        userDatabaseOperations.addUser(user);
        User userFromDB = userDatabaseOperations.getUser(username, password);

        Assert.assertEquals(username, userFromDB.getUsername());
        Assert.assertEquals(password, userFromDB.getPassword());
    }

    @Ignore
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

    @Ignore
    @Test
    public void addProductToOrder() {
        order = orderDatabaseOperations.createOrder(user, null);
        orderDatabaseOperations.addProduct(productId, order);
        Assert.assertTrue(orderDatabaseOperations.isProductInOrder(productId, order));
    }

    @Ignore
    @Test(dependsOnMethods = "addProductToOrder")
    public void removeProductFromOrder() {
        orderDatabaseOperations.deleteProductFromOrder(productId, order);
        Assert.assertFalse(orderDatabaseOperations.isProductInOrder(productId, order));
    }
}
