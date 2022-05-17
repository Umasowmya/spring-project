package com.springproject.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping("/")
    public String home()
    {
        return "home";
    }


    @RequestMapping("/login")
    public String loginPage()
    {
        return "login";
    }


    @RequestMapping("/logout-success")
    public String logoutPage()
    {
        return "logout";
    }


    @RequestMapping("/access-denied")
    public String accessDenied()
    {
        return "access-denied";
    }

}
