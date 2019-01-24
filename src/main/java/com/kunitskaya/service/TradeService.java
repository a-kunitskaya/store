package com.kunitskaya.service;

import com.kunitskaya.entity.Order;
import com.kunitskaya.entity.Product;
import com.kunitskaya.service.database.ProductDatabaseOperations;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class TradeService {
    @Autowired
    private ProductDatabaseOperations productDatabaseOperations;
    @Autowired
    private Logger logger;

    public void addTestProducts() {
        try {
            for (int i = 0; i < RandomUtils.nextInt(7, 15); i++) {
                Product product = new Product();
                product.setId(String.valueOf(i));
                product.setName(RandomStringUtils.randomAlphabetic(1, 10));

                String price = String.format("%.2f", RandomUtils.nextDouble(10, 1000));
                product.setPrice(Double.parseDouble(price));
                productDatabaseOperations.addProduct(product);
            }
        } catch (DuplicateKeyException e) {
            logger.error("Test products already exist in app");
        }
    }
}
