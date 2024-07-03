package com.scm.scm20.services.serviceimpl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.scm.scm20.Entities.contact;
import com.scm.scm20.Entities.user;
import com.scm.scm20.Helper.ResourceIsNotFound;
import com.scm.scm20.repositories.contactRepo;
import com.scm.scm20.services.contactService;

import lombok.var;

@Service
public class contactServiceImpl implements contactService {

    @Autowired
    private contactRepo contactRepo;

    @Override
    public contact save(contact contact) {

        String contactId = UUID.randomUUID().toString();
        contact.setId(contactId);
        return contactRepo.save(contact);
    }

    @Override
    public contact update(contact contact) {
        var oldContact = contactRepo.findById(contact.getId())
                .orElseThrow(() -> new ResourceIsNotFound("Id is not found"));
        oldContact.setName(contact.getName());
        oldContact.setEmail(contact.getEmail());
        oldContact.setPhoneNumber(contact.getPhoneNumber());
        oldContact.setAddress(contact.getAddress());
        oldContact.setDescription(contact.getDescription());
        oldContact.setLinkedinlink(contact.getLinkedinlink());
        oldContact.setWebsiteLink(contact.getWebsiteLink());
        oldContact.setFavourite(contact.isFavourite());
        oldContact.setPicture(contact.getPicture());
        oldContact.setCloudinaryImagePublicId(contact.getCloudinaryImagePublicId());

        return contactRepo.save(oldContact);

    }

    @Override
    public List<contact> getAll() {
        return contactRepo.findAll();
    }

    @Override
    public contact getById(String id) {
        return contactRepo.findById(id)
                .orElseThrow(() -> new ResourceIsNotFound("Contact not found by given id : " + id));
    }

    @Override
    public void delete(String id) {
        var contact = contactRepo.findById(id)
                .orElseThrow(() -> new ResourceIsNotFound("Contact not found by given id : " + id));
        contactRepo.delete(contact);
    }

    @Override
    public List<contact> getByUserId(String userId) {
        return contactRepo.findByUserId(userId);
    }

    @Override
    public Page<contact> getByUser(user user, int page, int size, String sortBy, String direction) {

        Sort sort = direction.equals("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

        var pageable = PageRequest.of(page, size, sort);

        return contactRepo.findByuser(user, pageable);

    }

    @Override
    public Page<contact> searchByName(String name, int size, int page, String SortBy, String order, user user) {
        Sort sort = order.equals("desc") ? Sort.by(SortBy).descending() : Sort.by(SortBy).ascending();
        var pageable = PageRequest.of(page, size, sort);
        return contactRepo.findByUserAndNameContaining(user, name, pageable);
    }

    @Override
    public Page<contact> searchByEmail(String email, int size, int page, String SortBy, String order, user user) {
        Sort sort = order.equals("desc") ? Sort.by(SortBy).descending() : Sort.by(SortBy).ascending();
        var pageable = PageRequest.of(page, size, sort);
        return contactRepo.findByUserAndEmailContaining(user, email, pageable);
    }

    @Override
    public Page<contact> searchByPhoneNumber(String phoneNumber, int size, int page, String SortBy, String order,
            user user) {
        Sort sort = order.equals("desc") ? Sort.by(SortBy).descending() : Sort.by(SortBy).ascending();
        var pageable = PageRequest.of(page, size, sort);
        return contactRepo.findByUserAndPhoneNumberContaining(user, phoneNumber, pageable);
    }

}
