package com.scm.scm20.config;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.scm.scm20.Entities.porviders;
import com.scm.scm20.Entities.user;
import com.scm.scm20.Helper.AppConstants;
import com.scm.scm20.repositories.userRepo;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class oauthenticationSucessHandler implements AuthenticationSuccessHandler {

    Logger logger = LoggerFactory.getLogger(oauthenticationSucessHandler.class);

    @Autowired
    private userRepo userRepo;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        logger.info("oauthenticationSucessHandler");
        DefaultOAuth2User user = (DefaultOAuth2User) authentication.getPrincipal();

        // logger.info(user.getName());

        // user.getAttributes().forEach((key, value) -> {
        // logger.info("{} => {}", key, value);
        // });

        // logger.info(user.getAuthorities().toString());

        String email = user.getAttribute("email").toString();
        String name = user.getAttribute("name").toString();
        String picture = user.getAttribute("picture").toString();

        user user2 = new user();
        user2.setEmail(email);
        user2.setName(name);
        user2.setProfilepic(picture);
        user2.setPassword("password");
        user2.setUserId(UUID.randomUUID().toString());
        user2.setProvider(porviders.GOOGLE);
        user2.setEnable(true);
        user2.setVarifiedEmail(true);
        user2.setProviderId(user2.getUsername());
        user2.setRole(List.of(AppConstants.ROLE_USER));
        user2.setAbout("This  profile is created by Google");

        user user3 = userRepo.findByEmail(email).orElse(null);
        if (user3 == null) {
            userRepo.save(user2);
            logger.info("Saved User", user2);
        }

        new DefaultRedirectStrategy().sendRedirect(request, response, "/user/profile");
    }

}
