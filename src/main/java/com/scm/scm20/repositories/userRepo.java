package com.scm.scm20.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scm.scm20.Entities.user;
import java.util.Optional;

public interface userRepo extends JpaRepository<user, String> {
    public Optional<user> findByEmail(String email);

}
