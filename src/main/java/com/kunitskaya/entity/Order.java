package com.kunitskaya.entity;

import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Component
public class Order {
    private List<Product> products;
    private double totalPrice;
    private OrderStatus status;
    @NotNull(message = "id is required")
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

    @Override
    public String toString() {
        return "Order{" +
                "products=" + products +
                ", totalPrice=" + totalPrice +
                ", status=" + status +
                ", id='" + id + '\'' +
                '}';
    }
}
