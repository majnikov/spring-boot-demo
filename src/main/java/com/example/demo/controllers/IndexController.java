package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by maksim on 6/21/17.
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("name", "World!");
        return "index";
    }

    @RequestMapping("/{name}")
    public String namedIndex(@PathVariable String name,  Model model) {
        model.addAttribute("name", name);
        return "index";
    }
}
