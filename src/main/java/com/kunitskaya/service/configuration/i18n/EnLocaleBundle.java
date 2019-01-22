package com.kunitskaya.service.configuration.i18n;

import java.util.ListResourceBundle;

public class EnLocaleBundle extends ListResourceBundle {
    public Object[][] getContents() {
        return contents;
    }

    private static final Object[][] contents = {
            {"username", "Username"},
            {"password", "Password"},
            {"logInBtn", "LOG IN"},
            {"registrationBtn", "REGISTRATION"},
            {"vendor", "VENDOR"},
            {"customer", "CUSTOMER"},
            {"welcome", "THANKS FOR JOINING TRADE SYSTEM, WELCOME!"},
            {"successRegistrationMsg", "Your registration was successful. Please log in now"},
            {"role", "User role"},
            {"addProductMsg", "Enter product id to add to your cart"},
            {"addBtn", "ADD TO ORDER"},
            {"removeBtn", "REMOVE FROM ORDER"},
            {"viewOrderBtn", "VIEW ORDER"},
            {"viewOrderMsg", "Here is you order. Current order status = CREATED"},
            {"checkoutBtn", "CHECKOUT"},
            {"checkoutMsg", "Thanks for your order. Current order status = COMPLETED, we will deliver it shortly. Please click 'CANCEL' to cancel your order"},
            {"cancelBtn", "CANCEL"},
            {"cancelledOrderMsg", "Your order has been cancelled. Please continue shopping to create a new one"}
    };
}