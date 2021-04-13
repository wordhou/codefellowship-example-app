package com.edhou.codefellowship.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
    @RequestMapping(value="/login", method= RequestMethod.GET)
    public String getLoginPage() {
        return "login.html";
    }

    @RequestMapping(value="/signup", method= RequestMethod.GET)
    public String getSignupPage(String error) {
        return "signup.html";
    }

    @RequestMapping(value="/error", method= RequestMethod.GET)
    public String getErrorPage() {
        return "error.html";
    }
}
