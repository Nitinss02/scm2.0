package com.scm.scm20.controller;

import java.util.UUID;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.scm.scm20.Entities.contact;
import com.scm.scm20.Entities.user;
import com.scm.scm20.Helper.AppConstants;
import com.scm.scm20.Helper.Helper;
import com.scm.scm20.Helper.message;
import com.scm.scm20.Helper.messageType;
import com.scm.scm20.forms.Contactform;
import com.scm.scm20.forms.contactSearchForm;
import com.scm.scm20.services.ImageService;
import com.scm.scm20.services.contactService;
import com.scm.scm20.services.userServices;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user/contact")
public class contactController {
    Logger logger = org.slf4j.LoggerFactory.getLogger(contactController.class);

    @Autowired
    private ImageService imageService;

    // public contactController(ImageService imageService) {
    // this.imageService = imageService;
    // }

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

        // image processing
        String filename = UUID.randomUUID().toString();

        String FileURL = imageService.UploadImage(contactform.getContactImage(), filename);
        // logger.info("file information : {}",
        // contactform.getContactImage().getOriginalFilename());
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
        contact.setPicture(FileURL);
        contact.setCloudinaryImagePublicId(filename);

        contactService.save(contact);

        httpSession.setAttribute("message",
                message.builder().content("Your contact Sucessfully Added").type(messageType.green).build());
        System.out.println(contactform);

        return "redirect:/user/contact/add";
    }

    // view contact
    @RequestMapping
    public String viewContact(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = AppConstants.PAGE_SIZE + "") int size,
            @RequestParam(value = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(value = "direction", defaultValue = "asc") String direction, Model model,
            Authentication authentication) {

        // load the all user contact
        String username = Helper.getEmailOfLoggedInUser(authentication);
        user user = userServices.getUserByEmail(username);
        Page<contact> pagecontacts = contactService.getByUser(user, page, size, sortBy, direction);
        model.addAttribute("pagecontacts", pagecontacts);
        model.addAttribute("pageSize", AppConstants.PAGE_SIZE);
        model.addAttribute("contactSearchForm", new contactSearchForm());
        return "/user/contact";
    }

    // serach Handler

    @RequestMapping(value = "/search")
    public String serachHandler(
            @ModelAttribute contactSearchForm contactSearchForm,
            @RequestParam(value = "size", defaultValue = AppConstants.PAGE_SIZE + " ") int size,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "sortby", defaultValue = "name") String sortby,
            @RequestParam(value = "direction", defaultValue = "asc") String direction,
            Model model, Authentication authentication) {
        // logger.info("keyword{} value{}", field, keyword);
        var user = userServices.getUserByEmail(Helper.getEmailOfLoggedInUser(authentication));
        Page<contact> pagecontacts = null;
        if (contactSearchForm.getField().equalsIgnoreCase("name")) {
            pagecontacts = contactService.searchByName(contactSearchForm.getKeywoard(), size, page, sortby, direction,
                    user);
        } else if (contactSearchForm.getField().equalsIgnoreCase("email")) {
            pagecontacts = contactService.searchByEmail(contactSearchForm.getKeywoard(), size, page, sortby, direction,
                    user);
        } else if (contactSearchForm.getField().equalsIgnoreCase("phoneNumber")) {
            pagecontacts = contactService.searchByPhoneNumber(contactSearchForm.getKeywoard(), size, page, sortby,
                    direction, user);
        }

        System.out.println("This  is an search filed \n");
        logger.info("pageContact {}", pagecontacts);
        model.addAttribute("contactSearchForm", contactSearchForm);
        model.addAttribute("pagecontacts", pagecontacts);
        model.addAttribute("pageSize", AppConstants.PAGE_SIZE);

        return "user/search";
    }

    // delete contact

    @RequestMapping("/delete/{contactId}")
    public String DeleteHandler(@PathVariable("contactId") String contactId, HttpSession session) {
        contactService.delete(contactId);
        logger.info("contact {} deleted", contactId);
        session.setAttribute("message",
                message.builder().content("contact Delete Sucessfully").type(messageType.blue).build());
        return "redirect:/user/contact";
    }

    @GetMapping("/view/{contactId}")
    public String updateContactView(@PathVariable("contactId") String contactId, Model model) {
        var contact = contactService.getById(contactId);
        Contactform contactform = new Contactform();
        contact.setName(contactform.getName());
        contact.setEmail(contactform.getEmail());
        contact.setPhoneNumber(contactform.getPhoneNumber());
        contact.setAddress(contactform.getAddress());
        contact.setDescription(contactform.getDescription());
        contact.setWebsiteLink(contactform.getWebsitelink());
        contact.setLinkedinlink(contactform.getLinkedinlink());
        contact.setFavourite(contactform.isFavourite());
        contact.setPicture(contactform.getPicture());
        model.addAttribute("contactform", contactform);
        model.addAttribute("contactId", contactId);
        return "user/upadateContactView";
    }

    @RequestMapping(value = "/update/{contactId}", method = RequestMethod.POST)
    public String UpdateContact(@PathVariable("contactId") String contactId,
            @Valid @ModelAttribute Contactform contactForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "user/upadateContactView";
        }
        var con = contactService.getById(contactId);
        con.setId(contactId);
        con.setName(contactForm.getName());
        con.setEmail(contactForm.getEmail());
        con.setPhoneNumber(contactForm.getPhoneNumber());
        con.setAddress(contactForm.getAddress());
        con.setDescription(contactForm.getDescription());
        con.setFavourite(contactForm.isFavourite());
        con.setWebsiteLink(contactForm.getWebsitelink());
        con.setLinkedinlink(contactForm.getLinkedinlink());

        // process image:

        if (contactForm.getContactImage() != null && !contactForm.getContactImage().isEmpty()) {
            logger.info("file is not empty");
            String fileName = UUID.randomUUID().toString();
            String imageUrl = imageService.UploadImage(contactForm.getContactImage(), fileName);
            con.setCloudinaryImagePublicId(fileName);
            con.setPicture(imageUrl);
            contactForm.setPicture(imageUrl);

        } else {
            logger.info("file is empty");
        }

        var updateCon = contactService.update(con);
        logger.info("updated contact {}", updateCon);

        model.addAttribute("message", message.builder().content("Contact Updated !!").type(messageType.green).build());

        return "redirect:/user/contact/view/" + contactId;
    }

}
