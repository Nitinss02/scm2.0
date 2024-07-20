package com.scm.scm20.services;

public interface EmailService {

    public void sendEmail(String to, String subject, String body);

    public void emailAttachement();
}
