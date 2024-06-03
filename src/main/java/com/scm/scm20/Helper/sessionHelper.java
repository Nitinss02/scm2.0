package com.scm.scm20.Helper;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import jakarta.servlet.http.HttpSession;

@Component
public class sessionHelper {

    public static void RemoveMessage() {
        try {
            System.out.println("Remove the message");
            HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest()
                    .getSession();
            session.removeAttribute("message");

        } catch (Exception e) {
            System.out.println("Error in sessionHelper " + e);
        }
    }
}
