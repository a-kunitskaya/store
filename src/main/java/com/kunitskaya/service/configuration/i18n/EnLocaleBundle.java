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
            {"welcome", "THANKS FOR JOINING TRADE-SYSTEM, WELCOME!"},
            {"successRegistrationMsg", "Your registration was successful. Please log in now"},
            {"role", "User role"}
    };
}