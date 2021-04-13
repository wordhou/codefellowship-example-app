package com.edhou.codefellowship.controllers;

import com.edhou.codefellowship.models.ApplicationUser;
import com.edhou.codefellowship.services.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class UserController {
    @Autowired
    UserRepository repo;

    @Autowired
    PasswordEncoder encoder;

    @RequestMapping(value="/users", method = RequestMethod.POST)
    public RedirectView create(String username, String password) {
        if (repo.existsByUsername(username))
            return new RedirectView("/signup?error=username_taken");
        ApplicationUser user = new ApplicationUser(username, encoder.encode(password));
        repo.save(user);
        return new RedirectView("/");
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
