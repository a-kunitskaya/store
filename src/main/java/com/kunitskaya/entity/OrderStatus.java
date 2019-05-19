package com.kunitskaya.entity;

import org.springframework.stereotype.Service;

@Service
public enum OrderStatus {
    CREATED,
    SHIPPED,
    CANCELLED,
}
