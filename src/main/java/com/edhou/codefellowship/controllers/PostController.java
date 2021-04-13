package com.edhou.codefellowship.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class PostController {
    @RequestMapping(value="/posts/", method = RequestMethod.POST)
    public RedirectView create() {
        return new RedirectView("/posts/");
    }

    @RequestMapping(value="/posts/{postId}", method = RequestMethod.GET)
    public String getDetail() {
        return "post.html";
    }

    @RequestMapping(value="/posts/{postId}", method = RequestMethod.PUT)
    public RedirectView edit() {
        return new RedirectView("/posts/{postId}");
    }

    @RequestMapping(value="/posts/{postId}", method = RequestMethod.DELETE)
    public RedirectView delete() {
        return new RedirectView("/posts/");
    }
}
