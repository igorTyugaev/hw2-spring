package com.ityugaev.springhw.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String main(Model model) {
        model.addAttribute("title", "Главная");
        return "login";
    }

}