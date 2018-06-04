package com.fiiadmission.service;

public interface EmailService {
    public void sendEmail(String subject, String to, String bodyText, String bodyHtml);

}
