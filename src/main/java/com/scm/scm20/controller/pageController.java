package com.scm.scm20.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.scm.scm20.Entities.user;
import com.scm.scm20.Helper.message;
import com.scm.scm20.Helper.messageType;
import com.scm.scm20.forms.userform;

import com.scm.scm20.services.userServices;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class pageController {

    @Autowired
    private userServices userServices;

    @GetMapping("/home")
    public String homeHandler(Model m) {
        m.addAttribute("name", "This is home page name");
        m.addAttribute("education", "Bachlor of computer scicence");
        m.addAttribute("google", "https://www.google.co.in/webhp");
        System.out.println("Home page Handler");
        return "home";
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/home";
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

    @GetMapping("/contact")
    public String contactHandler(Model model) {
        return "contact";
    }

    @GetMapping("/login")
    public String loginHandler(Model model) {
        return "login";
    }

    @GetMapping("/register")
    public String registerHandler(Model model) {
        userform userForm = new userform();
        // userForm.setName("");
        // userForm.setEmail("");
        // userForm.setPassword("nitin123");
        // userForm.setMobileNumber("98574920321");
        // userForm.setAbout("SomeThing About YourSelf");
        model.addAttribute("userform", userForm);
        return "register";
    }

    @PostMapping("/do-register")
    public String processRegister(@Valid @ModelAttribute userform userform, BindingResult rBindingResult,
            HttpSession session) {

        // user user2 = user.builder()
        // .name(userform.getName())
        // .Email(userform.getEmail())
        // .password(userform.getPassword())
        // .about(userform.getAbout())
        // .mobileNumber(userform.getMobileNumber())
        // .profilepic("/images/nature_background.jpg")
        // .build();

        if (rBindingResult.hasErrors()) {
            return "register";
        }

        user user2 = new user();
        user2.setName(userform.getName());
        user2.setEmail(userform.getEmail());
        user2.setPassword(userform.getPassword());
        user2.setAbout(userform.getAbout());
        user2.setMobileNumber(userform.getMobileNumber());
        user2.setProfilepic("/images/nature_background.jpg");
        user saveUser = userServices.saveUser(user2);
        System.out.println(saveUser);
        System.out.println("Process Registration");

        message msg = message.builder().content("Registration Sucessfully").type(messageType.yellow).build();
        session.setAttribute("message", msg);
        return "redirect:/login";
    }
}
