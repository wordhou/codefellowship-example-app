package com.edhou.codefellowship.config;

import com.edhou.codefellowship.services.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;
import java.util.Optional;

@ControllerAdvice
public class ModelAttributeConfig {
    @Autowired
    UserRepository userRepo;

    @ModelAttribute
    public void addUser(Model model, Principal principal) {
        // Populates the user attribute of the model if a user is currently authenticated.
        Optional.ofNullable(principal)
                .flatMap(p -> userRepo.findByUsername(principal.getName()))
                .ifPresent(u -> model.addAttribute("user", u));

//        if(principal != null) {
//            ApplicationUser user = userRepo.getByUsername(principal.getName());
//            model.addAttribute("user", user);
//        }
    }

}
