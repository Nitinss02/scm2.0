package com.scm.scm20.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.scm20.Entities.contact;
import com.scm.scm20.Entities.user;
import com.scm.scm20.Helper.Helper;
import com.scm.scm20.Helper.message;
import com.scm.scm20.Helper.messageType;
import com.scm.scm20.forms.Contactform;
import com.scm.scm20.services.contactService;
import com.scm.scm20.services.userServices;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user/contact")
public class contactController {

    @Autowired
    private contactService contactService;

    @Autowired
    private userServices userServices;

    @RequestMapping("/add")
    public String addContact(Model model) {
        Contactform contactform = new Contactform();

        model.addAttribute("contactform", contactform);
        return "user/add_contact";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String SaveConatact(@Valid @ModelAttribute Contactform contactform, BindingResult bindingResult,
            Authentication authentication, HttpSession httpSession) {

        if (bindingResult.hasErrors()) {
            httpSession.setAttribute("message",
                    message.builder().content("Pleses correct the following errors").type(messageType.red).build());
            return "/user/add_contact";
        }
        // process the form data
        String username = Helper.getEmailOfLoggedInUser(authentication);
        user users = userServices.getUserByEmail(username);

        // form -> data
        contact contact = new contact();
        contact.setName(contactform.getName());
        contact.setEmail(contactform.getEmail());
        contact.setPhoneNumber(contactform.getPhoneNumber());
        contact.setAddress(contactform.getAddress());
        contact.setDescription(contactform.getDescription());
        contact.setWebsiteLink(contactform.getWebsitelink());
        contact.setLinkedinlink(contactform.getLinkedinlink());
        contact.setFavourite(contactform.isFavourite());
        contact.setUser(users);

        contactService.save(contact);

        httpSession.setAttribute("message",
                message.builder().content("Your contact Sucessfully Added").type(messageType.green).build());
        System.out.println(contactform);

        return "redirect:/user/contact/add";
    }

}
