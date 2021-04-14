package com.edhou.codefellowship.controllers;

import com.edhou.codefellowship.models.ApplicationUser;
import com.edhou.codefellowship.services.FileUploadService;
import com.edhou.codefellowship.services.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    UserRepository repo;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    FileUploadService fileUploadService;

    @RequestMapping(value="/users", method = RequestMethod.GET)
    public String getUsers(Model model) {
        List<ApplicationUser> users = repo.findAll();
        model.addAttribute("users", users);
        return "users.html";
    }

    @RequestMapping(value="/users", method = RequestMethod.POST)
    public RedirectView create(String username, String password) {
        if (repo.existsByUsername(username))
            return new RedirectView("/signup?error=username_taken");
        ApplicationUser user = new ApplicationUser(username, encoder.encode(password));
        repo.save(user);
        return new RedirectView("/");
    }

    @RequestMapping(value="/username", method = RequestMethod.GET)
    @ResponseBody
    public String getCurrentUsername(Principal principal) {
        return principal.getName();
    }

    @RequestMapping(value="/users/{userId}", method = RequestMethod.GET)
    public String getDetail(@PathVariable long userId, Model model) {
        ApplicationUser userSubject = repo.getOne(userId);
        model.addAttribute("userSubject", userSubject);
        return "user.html";
    }

    @RequestMapping(value="/users/{userId}", method = RequestMethod.PUT)
    public RedirectView edit(@PathVariable long userId,
                             String firstName,
                             String lastName,
                             String bio,
                             //Date dateOfBirth,
                             Principal principal) {
        ApplicationUser user = repo.getOne(userId);
        if (!principal.getName().equals(user.getUsername()))
            throw new AuthorizationServiceException("");
        if (firstName != null) user.setFirstName(firstName);
        if (lastName != null) user.setLastName(lastName);
        if (bio != null) user.setBio(bio);
        //if (dateOfBirth != null) user.setDateOfBirth(dateOfBirth);
        repo.save(user);

        return new RedirectView("/users/" + user.getId());
    }

    @RequestMapping(value="/users/{userId}/image", method = RequestMethod.POST)
    public RedirectView setProfilePicture(@PathVariable long userId,
                             Principal principal,
                             @RequestParam("image") MultipartFile upload
                             ) throws IOException {
        ApplicationUser user = repo.getOne(userId);
        if (!principal.getName().equals(user.getUsername()))
            throw new AuthorizationServiceException("");
        String fileName = StringUtils.cleanPath(upload.getOriginalFilename());
        String uploadDir = "" + user.getId();
        fileUploadService.saveFile(uploadDir, fileName, upload);
        user.setImageUrl(fileName);
        repo.save(user);
        return new RedirectView("/users/" + user.getId());
    }

    @RequestMapping(value="/users/{userId}", method = RequestMethod.DELETE)
    public RedirectView delete() {
        // TODO: unimplemented
        return new RedirectView("/");
    }
}
