package com.edhou.codefellowship.services;

import org.springframework.stereotype.Service;

@Service
public class PasswordValidator {
    public boolean validate(String password) {
        return password.matches(".*[a-zA-Z].*") && password.matches(".*[0-9].*");
    }

    public String getRulesDescription() {
        return "Password must contain a number and a letter.";
    }
}
