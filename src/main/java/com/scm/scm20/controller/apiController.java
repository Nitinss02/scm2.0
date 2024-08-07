package com.scm.scm20.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scm.scm20.Entities.contact;
import com.scm.scm20.services.contactService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api")
public class apiController {

    @Autowired
    private contactService contactService;

    @RequestMapping("/contact/{contactId}")
    public contact getContact(@PathVariable String contactId) {
        return contactService.getById(contactId);
    }

}
