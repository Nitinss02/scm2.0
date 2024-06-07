package com.scm.scm20.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.scm20.services.userServices;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/user")
public class Usercontroller {
    // user dashbord page

    private Logger logger = LoggerFactory.getLogger(Usercontroller.class);

    @Autowired
    private userServices userService;

    @RequestMapping(value = "/dashboard")
    public String userDashboard() {
        System.out.println("User dashboard");
        return "user/dashboard";
    }

    // add user contact page

    @RequestMapping(value = "/signup")
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
