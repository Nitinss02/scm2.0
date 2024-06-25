package com.scm.scm20.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.scm.scm20.Entities.contact;
import com.scm.scm20.Entities.user;

@Repository
@EnableJpaRepositories
public interface contactRepo extends JpaRepository<contact, String> {

    // custom finder method
    Page<contact> findByuser(user user, Pageable pageable);

    // custom Query method
    @Query("SELECT c FROM contact c WHERE c.user.id = :userId")
    List<contact> findByUserId(@Param("userId") String userId);

    Page<contact> findByUserAndNameContaining(user user, String name, Pageable pageable);

    Page<contact> findByUserAndEmailContaining(user user, String email, Pageable pageable);

    Page<contact> findByUserAndPhoneNumberContaining(user user, String phoneNumber, Pageable pageable);
}
