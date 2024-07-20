package com.scm.scm20.config;

import java.io.IOException;

import org.springframework.boot.autoconfigure.jms.JmsProperties.Listener.Session;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.scm.scm20.Helper.message;
import com.scm.scm20.Helper.messageType;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class AuthFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
        if (exception instanceof DisabledException) {
            HttpSession session = request.getSession();
            session.setAttribute(
                    "message",
                    message.builder().content("User is Disabled, Email verification link is send to your Email")
                            .type(messageType.red).build());
            response.sendRedirect("/login");
        } else {
            response.sendRedirect("/login?error=true");
        }
    }

}
