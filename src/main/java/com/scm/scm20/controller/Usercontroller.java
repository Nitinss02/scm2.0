package com.scm.scm20.controller;

import org.springframework.ui.Model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class Usercontroller {
    // user dashbord page

    private Logger logger = LoggerFactory.getLogger(Usercontroller.class);

    @RequestMapping(value = "/dashboard")
    public String userDashboard() {

        System.out.println("User dashboard");

        return "user/dashboard";
    }

    // add user contact page

    @RequestMapping(value = "/signup")
    public String usersignup() {
        logger.info("Its is a user signUp page");
        return "user/signup";
    }
    // user view page

    @RequestMapping("/profile")
    public String UserProfile(Model model, Authentication authentication) {

        return "user/profile";
    }
    // user edit page

    // user delete page

    // user search page
}
