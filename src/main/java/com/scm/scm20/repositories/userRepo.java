package com.scm.scm20.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scm.scm20.Entities.user;
import java.util.Optional;

@Repository
public interface userRepo extends JpaRepository<user, String> {
    Optional<user> findByEmail(String email);

    Optional<user> findByEmailAndPassword(String email, String password);

}
