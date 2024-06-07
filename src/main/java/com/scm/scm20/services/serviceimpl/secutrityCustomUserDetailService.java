package com.scm.scm20.services.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.scm.scm20.repositories.userRepo;

@Service
public class secutrityCustomUserDetailService implements UserDetailsService {

    @Autowired
    private userRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // apne user ko load karana hai

        return userRepo.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("user is not found with this " + username));

    }

}