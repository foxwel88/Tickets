package edu.nju.tickets.service;

import org.springframework.messaging.MessagingException;

import java.io.UnsupportedEncodingException;

/**
 * Created by foxwel on 2018/3/11.
 */
public interface MailService {
    void sendEmail(String to, String subject, String context) throws MessagingException, UnsupportedEncodingException, javax.mail.MessagingException;
}
