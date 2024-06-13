package com.scm.scm20.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/contact")
public class contactController {

    @RequestMapping("/add")
    public String addContact() {
        return "user/add_contact";
    }
}
