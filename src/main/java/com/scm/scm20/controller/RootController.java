package com.scm.scm20.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.scm.scm20.Entities.user;
import com.scm.scm20.Helper.Helper;
import com.scm.scm20.services.userServices;

@ControllerAdvice
public class RootController {

    Logger logger = LoggerFactory.getLogger(RootController.class);

    @Autowired
    userServices userServices;

    @ModelAttribute
    public void addLoggedInUserInformation(Model model, Authentication authentication) {
        if (authentication == null) {
            return;
        }
        System.out.println("Adding logged in user information to the model");

        String username = Helper.getEmailOfLoggedInUser(authentication);
        logger.info("User logged in: {}", username);
        // database se data ko fetch : get user from db :
        user user = userServices.getUserByEmail(username);
        System.out.println(user);
        System.out.println(user.getName());
        System.out.println(user.getEmail());
        model.addAttribute("loggedInUser", user);

    }

    // @ModelAttribute
    // public void LoggedinUserInformation(Model model, Authentication
    // authentication) {
    // String username = Helper.getEmailofloggedinUser(authentication);
    // user user = userServices.getUserByEmail(username);
    // model.addAttribute("name", "nitin sonawane");
    // model.addAttribute("loggedinUser", user);
    // System.out.println(user);
    // logger.info("User name is : {}", username);
    // System.out.println("user profile");

    // }
}
