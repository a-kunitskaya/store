package com.kunitskaya.service;

import com.kunitskaya.entity.User;
import com.kunitskaya.service.database.UserDatabaseOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class MainController {

    @RequestMapping(method = RequestMethod.GET)
    public String getIndex() {
        return "index";
    }

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public String logIn(Model model, @ModelAttribute("username") String username,  @ModelAttribute("password") String password) {
        UserDatabaseOperations userDatabaseOperations = new UserDatabaseOperations();
        User user = userDatabaseOperations.getUser(username, password);
        if (user != null) {
            model.addAttribute("authorized_user", user.getUsername());
        }

        return "products";

    }
}
