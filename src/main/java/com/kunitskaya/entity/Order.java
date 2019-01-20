package com.kunitskaya.entity;

import java.util.ArrayList;
import java.util.List;

//Task 1 Purchase
public class Order {
    private List<Product> products;
    private double totalPrice;
    private OrderStatus status;
    private String id;

    public List<Product> getProducts() {
        if(products == null){
            products = new ArrayList<>();
        }
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
