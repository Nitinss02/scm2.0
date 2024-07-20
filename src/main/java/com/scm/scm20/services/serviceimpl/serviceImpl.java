package com.scm.scm20.services.serviceimpl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.scm.scm20.Entities.user;
import com.scm.scm20.Helper.ResourceIsNotFound;
import com.scm.scm20.Helper.AppConstants;
import com.scm.scm20.Helper.Helper;
import com.scm.scm20.repositories.userRepo;
import com.scm.scm20.services.EmailService;
import com.scm.scm20.services.userServices;

@Service
public class serviceImpl implements userServices {
    @Autowired
    private userRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService mailService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public user saveUser(user user) {
        // user id : have to generate
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);
        // password encode
        // user.setPassword(userId);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // set the user role

        user.setRole(List.of(AppConstants.ROLE_USER));

        logger.info(user.getProvider().toString());

        String emailToken = UUID.randomUUID().toString();
        user.setEmailToken(emailToken);
        user saveduser = userRepo.save(user);
        String emailLink = Helper.getLinkForEmailVerificatiton(emailToken);
        mailService.sendEmail(saveduser.getEmail(), "Verify Account : Smart Contact Manager", emailLink);
        return saveduser;

    }

    @Override
    public Optional<user> getUserById(String id) {
        return userRepo.findById(id);
    }

    @Override
    public Optional<user> updateUser(user user) {

        user user2 = userRepo.findById(user.getUserId()).orElseThrow(() -> new ResourceIsNotFound("User Is Not Found"));
        // update karenge user2 from user
        user2.setName(user.getName());
        user2.setEmail(user.getEmail());
        user2.setPassword(user.getPassword());
        user2.setAbout(user.getAbout());
        user2.setMobileNumber(user.getMobileNumber());
        user2.setProfilepic(user.getProfilepic());
        user2.setEnable(user.isEnable());
        user2.setVarifiedEmail(user.isVarifiedEmail());
        user2.setVarifiedMobile(user.isVarifiedMobile());
        user2.setProvider(user.getProvider());
        user2.setProviderId(user.getProviderId());
        // save the user in database
        user save = userRepo.save(user2);
        return Optional.ofNullable(save);

    }

    @Override
    public void deleteuser(String id) {
        user user2 = userRepo.findById(id).orElseThrow(() -> new ResourceIsNotFound("User not found"));
        userRepo.delete(user2);

    }

    @Override
    public boolean isUserExit(String id) {
        user user2 = userRepo.findById(id).orElse(null);
        return user2 != null ? true : false;
    }

    @Override
    public List<user> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public user getUserByEmail(String email) {
        return userRepo.findByEmail(email).orElse(null);

    }

    @Override
    public boolean isuserExistByEmail(String email) {
        user user = userRepo.findByEmail(email).orElse(null);
        return user != null ? true : false;
    }

}
