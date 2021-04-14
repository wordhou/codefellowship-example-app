package com.edhou.codefellowship.controllers;

import com.edhou.codefellowship.models.ApplicationUser;
import com.edhou.codefellowship.models.Post;
import com.edhou.codefellowship.services.PostRepository;
import com.edhou.codefellowship.services.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import javax.websocket.server.PathParam;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class PostController {
    @Autowired
    PostRepository repo;

    @Autowired
    UserRepository userRepo;

    @RequestMapping(value="/editor", method = RequestMethod.GET)
    public String getEditorNewPost() {
        return "editor.html";
    }

    @RequestMapping(value="/editor/{postId}", method = RequestMethod.GET)
    public String getEditorExistingPost(@PathVariable long postId, Model model) {
        Optional.ofNullable(repo.getOne(postId))
                .ifPresent(p -> model.addAttribute("post", p));
        return "editor.html";
    }

    @RequestMapping(value="/posts", method = RequestMethod.POST)
    public RedirectView create(String body, Principal principal) {
        Optional<ApplicationUser> user = Optional.ofNullable(principal)
                .flatMap(p -> userRepo.findByUsername(p.getName()));
        if (user.isPresent()) {
            Post post = new Post(user.get(), body);
            repo.save(post);
            return new RedirectView("/posts/" + post.getId());
        } else {
            return new RedirectView("/login");
        }
    }

    @RequestMapping(value="/posts", method = RequestMethod.GET)
    public String getPosts(Model model) {
        List<Post> posts = repo.findAll();
        model.addAttribute("posts", posts);
        return "posts.html";
    }

    @RequestMapping(value="/posts/{postId}", method = RequestMethod.GET)
    public String getDetail(@PathVariable long postId, Model model) {
        Post post = repo.getOne(postId);
        model.addAttribute("post", post);
        return "post.html";
    }

    @RequestMapping(value="/posts/{postId}", method = RequestMethod.PUT)
    public RedirectView edit(@PathVariable long postId, String bio) {
        // TODO: unimplemented
        return new RedirectView("/posts/{postId}");
    }

    @RequestMapping(value="/posts/{postId}", method = RequestMethod.DELETE)
    public RedirectView delete() {
        // TODO: unimplemented
        return new RedirectView("/posts/");
    }
}
