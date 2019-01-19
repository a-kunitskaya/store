package com.kunitskaya.service;

import com.kunitskaya.entity.Product;
import com.kunitskaya.entity.User;
import com.kunitskaya.entity.UserRoles;
import com.kunitskaya.service.database.ProductDatabaseOperations;
import com.kunitskaya.service.database.UserDatabaseOperations;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {
    @Autowired
    private UserDatabaseOperations userDatabaseOperations;
    @Autowired
    private ProductDatabaseOperations productDatabaseOperations;
    @Autowired
    private User user;
    @Autowired
    private Logger logger;

    @RequestMapping(method = RequestMethod.GET)
    public String getIndex(Model model) {
        return "index";
    }

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public String logIn(Model model, @ModelAttribute("username") String username, @ModelAttribute("password") String password) {
        List<Product> products = productDatabaseOperations.getProducts();
        model.addAttribute("products", products);

        try {
            user = userDatabaseOperations.getUser(username, password);
            return "products";
        } catch (EmptyResultDataAccessException e) {
            String error = "No such user is found. Please register";
            model.addAttribute("error", error);
            return getIndex(model);
        }
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String getRegistration() {
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String register(@ModelAttribute("username") String username, @ModelAttribute("password") String password, @ModelAttribute("role") String role) {
        user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(UserRoles.valueOf(role));
        userDatabaseOperations.addUser(user);
        return "successRegistration";
    }

    @RequestMapping(value = "addProduct", method = RequestMethod.POST)
    public String addProductToCart(@ModelAttribute("productId") String productId) {
        logger.info("Adding product to cart, id: " + productId);

        productDatabaseOperations.addProductToCart(productId);
    }
}
