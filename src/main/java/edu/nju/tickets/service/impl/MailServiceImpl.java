package edu.nju.tickets.service.impl;

import edu.nju.tickets.service.MailService;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Service;

import javax.mail.SendFailedException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * Created by foxwel on 2018/3/11.
 */

@Service
public class MailServiceImpl implements MailService{
    private static final String HOST = "smtp.126.com";
    private static final Integer PORT = 25;
    private static final String USERNAME = "tickets_service@126.com";
    private static final String PASSWORD = "tickets741852963";
    private static final String EMAILFORM = "tickets_service@126.com";
    private static JavaMailSenderImpl mailSender = createMailSender();
    /**
     * 邮件发送器
     *
     * @return 配置好的工具
     */
    private static JavaMailSenderImpl createMailSender() {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(HOST);
        sender.setPort(PORT);
        sender.setUsername(USERNAME);
        sender.setPassword(PASSWORD);
        sender.setDefaultEncoding("Utf-8");
        Properties p = new Properties();
        p.setProperty("mail.smtp.timeout", "25000");
        p.setProperty("mail.smtp.auth", "false");
        sender.setJavaMailProperties(p);
        return sender;
    }

    /**
     * 发送邮件
     *
     * @param to 接受人
     * @param subject 主题
     * @param context 发送内容
     * @throws MessagingException 异常
     * @throws UnsupportedEncodingException 异常
     */
    @Override
    public void sendEmail(String to, String subject, String context) throws javax.mail.SendFailedException, MessagingException, UnsupportedEncodingException, javax.mail.MessagingException  {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "GB2312");
        messageHelper.setFrom(EMAILFORM, "Tickets");
        messageHelper.setTo(to);
        messageHelper.setSubject(subject);
        messageHelper.setText(context, true);
        mailSender.send(mimeMessage);
    }

}

