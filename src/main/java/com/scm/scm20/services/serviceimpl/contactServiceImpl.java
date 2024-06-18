package com.scm.scm20.services.serviceimpl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scm.scm20.Entities.contact;
import com.scm.scm20.Helper.ResourceIsNotFound;
import com.scm.scm20.repositories.contactRepo;
import com.scm.scm20.services.contactService;

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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
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
    public List<contact> serch(String name, String email, String phoneNumber) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'serch'");
    }

    @Override
    public List<contact> getByUserId(String userId) {
        return contactRepo.findByUserId(userId);
    }

}
