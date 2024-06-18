package com.scm.scm20.services;

import java.util.List;

import com.scm.scm20.Entities.contact;

public interface contactService {
    contact save(contact contact);

    contact update(contact contact);

    List<contact> getAll();

    contact getById(String id);

    void delete(String id);

    List<contact> serch(String name, String email, String phoneNumber);

    List<contact> getByUserId(String userId);
}
