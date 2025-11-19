package com.bds.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Home Controller
 * Handles requests for the home page
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home";
    }
}
