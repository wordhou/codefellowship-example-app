package com.edhou.codefellowship.controllers;

import com.edhou.codefellowship.models.ApplicationUser;
import com.edhou.codefellowship.services.PostRepository;
import com.edhou.codefellowship.services.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.Optional;

@Controller
public class MainController {
    @RequestMapping(value="/", method = RequestMethod.GET)
    public String getHomePage() {
        return "home.html";
    }

    @RequestMapping(value="/login", method= RequestMethod.GET)
    public String getLoginPage() {
        return "login.html";
    }

    @RequestMapping(value="/signup", method= RequestMethod.GET)
    public String getSignupPage(String error, Model model) {
        model.addAttribute("error", error);

        return "signup";
    }

    @RequestMapping(value="/error", method= RequestMethod.GET)
    public String getErrorPage() {
        return "error.html";
    }
}