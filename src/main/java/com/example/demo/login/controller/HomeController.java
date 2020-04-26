package com.example.demo.login.controller;

import com.example.demo.login.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    @Autowired
    UserService userService;

    @GetMapping("/home")
    public String getHome(Model model){

        // <div th:include="__${contents}__"></div> で使用する
        model.addAttribute("contents", "login/home :: home_contents");

        return "login/homeLayout";
    }

    @PostMapping("/logout")
    public String postLogout(){

        return "redirect:/login";
    }
}
