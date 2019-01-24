package com.kunitskaya.service;

import com.kunitskaya.entity.*;
import com.kunitskaya.service.database.OrderDatabaseOperations;
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
import java.util.Map;

@Controller
@RequestMapping("/")
public class MainController {
    @Autowired
    private UserDatabaseOperations userDatabaseOperations;
    @Autowired
    private ProductDatabaseOperations productDatabaseOperations;
    @Autowired
    private OrderDatabaseOperations orderDatabaseOperations;
    @Autowired
    private User user;
    @Autowired
    private Logger logger;
    private Order order;
    private String locale;
    private String localeLocation;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String selectLocale(@ModelAttribute("locale") String locale, @ModelAttribute("localeLocation") String localeLocation) {
        this.locale = locale;
        this.localeLocation = localeLocation;
        return "main";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String getIndex(Model model) {
        model.addAttribute("locale", locale);
        model.addAttribute("localeLocation", localeLocation);

        return "index";
    }

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public String viewProducts(Model model, @ModelAttribute("username") String username, @ModelAttribute("password") String password) {
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
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(UserRoles.valueOf(role));
        userDatabaseOperations.addUser(user);
        return "successRegistration";
    }

    @RequestMapping(value = "/addProduct", method = RequestMethod.POST)
    public String addProductToOrder(Model model, @ModelAttribute("productId") String productId) {
        logger.info("Adding product to order, id: " + productId);

        order = orderDatabaseOperations.createOrder(user, order);
        Product product = productDatabaseOperations.getProduct(productId);
        orderDatabaseOperations.addProduct(productId, order);
        order.getProducts().add(product);

        return viewProducts(model, user.getUsername(), user.getPassword());
    }

    @RequestMapping(value = "/deleteProduct", method = RequestMethod.POST)
    public String removeProductFromOrder(Model model, @ModelAttribute("productId") String productId) {
        logger.info("Removing product from order, id: " + productId);
        boolean isProductInOrder = orderDatabaseOperations.isProductInOrder(productId, order);
        if (isProductInOrder) {
            orderDatabaseOperations.deleteProductFromOrder(productId, order);
            Product product = productDatabaseOperations.getProduct(productId);
            order.getProducts().remove(product);
            logger.info("Product is removed from order");
        } else {
            String error = "Product is not in order. To delete a product please add it first";
            model.addAttribute("error", error);
            logger.info("Product is not removed from order");
        }

        return viewProducts(model, user.getUsername(), user.getPassword());
    }

    @RequestMapping(value = "/viewOrder", method = RequestMethod.GET)
    public String viewOrder(Model model) {
        logger.info("Getting products in order");
        Map<Product, Integer> orderProducts = orderDatabaseOperations.getProductCount(order);
        model.addAttribute("order", orderProducts);
        return "order";
    }

    @RequestMapping(value = "/cancelOrder", method = RequestMethod.GET)
    public String cancelOrder(Model model) {
        logger.info("Cancelling order: " + order.getId());
        order.setStatus(OrderStatus.CANCELLED);
        orderDatabaseOperations.deleteOrder(order);

        model.addAttribute("cancelledOrder", order);
        return viewProducts(model, user.getUsername(), user.getPassword());
    }

    @RequestMapping(value = "checkout", method = RequestMethod.GET)
    public String checkout() {
        return "checkout";
    }
}
