package com.scm.scm20.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class Usercontroller {
    // user dashbord page

    @RequestMapping("/dashboard")
    public String userDashboard() {
        return "user/dashboard";
    }

    // add user contact page

    @RequestMapping("/signup")
    public String usersignup() {
        return "user/signup";
    }
    // user view page

    @RequestMapping("/profile")
    public String UserProfile() {
        return "user/profile";
    }
    // user edit page

    // user delete page

    // user search page
}
