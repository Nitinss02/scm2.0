package com.scm.scm20.services;

import java.util.List;
import java.util.Optional;

import com.scm.scm20.Entities.user;

public interface userServices {
    user saveUser(user user);

    Optional<user> getUserById(String id);

    Optional<user> updateUser(user user);

    void deleteuser(String id);

    boolean isUserExit(String id);

    List<user> getAllUsers();

    boolean isuserExistByEmail(String email);

    user getUserByEmail(String email);

}
