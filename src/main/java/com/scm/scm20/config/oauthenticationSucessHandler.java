package com.scm.scm20.config;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.endpoint.DefaultOAuth2AccessTokenResponseMapConverter;
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
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        logger.info("oauthenticationSucessHandler");

        // identify the providre
        var OAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;

        String authorizedClientRegistrationId = OAuth2AuthenticationToken.getAuthorizedClientRegistrationId();

        logger.info(authorizedClientRegistrationId);

        var oauthuser = (DefaultOAuth2User) authentication.getPrincipal();

        oauthuser.getAttributes().forEach((key, value) -> {
            logger.info(key + " ->" + value);
        });

        user user = new user();
        user.setUserId(UUID.randomUUID().toString());
        user.setRole(List.of(AppConstants.ROLE_USER));
        user.setVarifiedEmail(true);
        user.setEnable(true);
        user.setPassword("dummy");

        if (authorizedClientRegistrationId.equalsIgnoreCase("google")) {

            user.setEmail(oauthuser.getAttribute("email"));
            user.setProfilepic(oauthuser.getAttribute("pictuer"));
            user.setName(oauthuser.getAttribute("name"));
            user.setProviderId(oauthuser.getName());
            user.setProvider(porviders.GOOGLE);
            user.setAbout("This account is created form google");
            // google authentication
        } else if (authorizedClientRegistrationId.equalsIgnoreCase("github")) {
            // github authentication

            String email = oauthuser.getAttribute("email") != null ? oauthuser.getAttribute("email")
                    : oauthuser.getAttribute("login") + "@gmail.com";
            String picture = oauthuser.getAttribute("avatar_url");
            String name = oauthuser.getAttribute("login");
            String providerId = oauthuser.getName();

            user.setEmail(email);
            user.setProfilepic(picture);
            user.setName(name);
            user.setProviderId(providerId);
            user.setProvider(porviders.GITHUB);
            user.setAbout("This account is created form GitHub");
        } else if (authorizedClientRegistrationId.equalsIgnoreCase("facebook")) {
            // facebook authentication
        } else {
            logger.info("authorizedClientRegistrationId : Unknown provider");
        }

        /*
         * DefaultOAuth2User user = (DefaultOAuth2User) authentication.getPrincipal();
         * 
         * // logger.info(user.getName());
         * 
         * // user.getAttributes().forEach((key, value) -> {
         * // logger.info("{} => {}", key, value);
         * // });
         * 
         * // logger.info(user.getAuthorities().toString());
         * 
         * String email = user.getAttribute("email").toString();
         * String name = user.getAttribute("name").toString();
         * String picture = user.getAttribute("picture").toString();
         * 
         * user user2 = new user();
         * user2.setEmail(email);
         * user2.setName(name);
         * user2.setProfilepic(picture);
         * user2.setPassword("password");
         * user2.setUserId(UUID.randomUUID().toString());
         * user2.setProvider(porviders.GOOGLE);
         * user2.setEnable(true);
         * user2.setVarifiedEmail(true);
         * user2.setProviderId(user2.getUsername());
         * user2.setRole(List.of(AppConstants.ROLE_USER));
         * user2.setAbout("This  profile is created by Google");
         * 
         * user user3 = userRepo.findByEmail(email).orElse(null);
         * if (user3 == null) {
         * userRepo.save(user2);
         * logger.info("Saved User", user2);
         * }
         */

        user user1 = userRepo.findByEmail(user.getEmail()).orElse(null);
        if (user1 == null) {
            userRepo.save(user);
            logger.info("Saved User", user);
        }
        new DefaultRedirectStrategy().sendRedirect(request, response, "/user/profile");
    }

}
