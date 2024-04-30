package com.scm.scm20.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class pageController {

    @GetMapping("/home")
    public String homeHandler(Model m) {
        m.addAttribute("name", "This is home page name");
        m.addAttribute("education", "Bachlor of computer scicence");
        m.addAttribute("google", "https://www.google.co.in/webhp");
        System.out.println("Home page Handler");
        return "home";
    }

    @GetMapping("/about")
    public String aboutHandler(Model model) {
        return "about";
    }

    @GetMapping("/service")
    public String serviceHandler(Model model) {
        model.addAttribute("isActive", false);
        return "service";
    }

}
