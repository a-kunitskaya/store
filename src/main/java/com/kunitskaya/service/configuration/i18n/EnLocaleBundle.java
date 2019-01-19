package com.kunitskaya.service.configuration.i18n;

import java.util.ListResourceBundle;

public class EnLocaleBundle extends ListResourceBundle {
    public Object[][] getContents() {
        return contents;
    }

    private static final Object[][] contents = {
            {"username", "Username"},
            {"password", "Password"},
            {"submit", "SUBMIT"},
            {"registration", "REGISTRATION"},
            {"vendor", "vendor"},
            {"customer", "customer"}
    };
}