package com.scm.scm20.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.scm.scm20.Entities.contact;
import com.scm.scm20.Entities.user;

public interface contactService {
    contact save(contact contact);

    contact update(contact contact);

    List<contact> getAll();

    contact getById(String id);

    void delete(String id);

    Page<contact> searchByName(String name, int size, int page, String SortBy, String order, user user);

    Page<contact> searchByEmail(String email, int size, int page, String SortBy, String order, user user);

    Page<contact> searchByPhoneNumber(String phoneNumber, int size, int page, String SortBy, String order, user user);

    List<contact> getByUserId(String userId);

    Page<contact> getByUser(user user, int page, int size, String sort, String direction);

}
