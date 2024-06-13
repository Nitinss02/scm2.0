package com.scm.scm20.Helper;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class Helper {
    public static String getEmailOfLoggedInUser(Authentication authentication) {

        if (authentication instanceof OAuth2AuthenticationToken) {

            var aOAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
            var clientId = aOAuth2AuthenticationToken.getAuthorizedClientRegistrationId();

            var oauth2User = (OAuth2User) authentication.getPrincipal();
            String username = "";

            if (clientId.equalsIgnoreCase("google")) {

                // sign with google
                System.out.println("Getting email from google");
                username = oauth2User.getAttribute("email").toString();

            } else if (clientId.equalsIgnoreCase("github")) {

                // sign with github
                System.out.println("Getting email from github");
                username = oauth2User.getAttribute("email") != null ? oauth2User.getAttribute("email").toString()
                        : oauth2User.getAttribute("login").toString() + "@gmail.com";
            }

            // sign with facebook
            return username;

        } else {
            System.out.println("Getting data from local database");
            return authentication.getName();
        }

        // if (authentication instanceof OAuth2AuthenticatedPrincipal) {

        // var OAuth2AccessToken = (OAuth2AuthenticationToken) authentication;
        // var clientid = OAuth2AccessToken.getAuthorizedClientRegistrationId();
        // var oauth2User = (OAuth2User) authentication.getPrincipal();
        // String username = null;

        // if (clientid.equalsIgnoreCase("google")) {
        // System.out.println("Getting email form google");
        // username = oauth2User.getAttribute("email");
        // }

        // else if (clientid.equalsIgnoreCase("github")) {
        // System.out.println("Getting email form github");
        // username = oauth2User.getAttribute("email") != null ?
        // oauth2User.getAttribute("email")
        // : oauth2User.getAttribute("login") + "@gmail.com";
        // }
        // System.out.println("its form virtual memeroy");
        // return username;
        // } else {
        // System.out.println("getting data form local database");
        // return authentication.getName();
        // }

    }
}
