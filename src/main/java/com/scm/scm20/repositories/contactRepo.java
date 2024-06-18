package com.scm.scm20.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.scm.scm20.Entities.contact;
import com.scm.scm20.Entities.user;

@Repository
public interface contactRepo extends JpaRepository<contact, String> {

    // custom finder method
    List<contact> findByuser(user user);

    // custom Query method
    @Query("SELECT c FROM contact c WHERE c.user.id = :userId")
    List<contact> findByUserId(@Param("userId") String userId);
}
