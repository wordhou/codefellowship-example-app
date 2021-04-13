package com.edhou.codefellowship.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class UserController {
    @RequestMapping(value="/users/", method = RequestMethod.POST)
    public RedirectView create() {
        return new RedirectView("/users/");
    }

    @RequestMapping(value="/users/{userId}", method = RequestMethod.GET)
    public String getDetail() {
        return "user.html";
    }

    @RequestMapping(value="/users/{userId}", method = RequestMethod.PUT)
    public RedirectView edit() {
        return new RedirectView("/users/{userId}");
    }

    @RequestMapping(value="/users/{userId}", method = RequestMethod.DELETE)
    public RedirectView delete() {
        return new RedirectView("/");
    }
}
