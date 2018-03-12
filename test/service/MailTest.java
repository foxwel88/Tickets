package service;

import edu.nju.tickets.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@ContextConfiguration("/WEB-INF/applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)

public class MailTest {

    @Autowired
    private MailService mailService;




    @Test
    public void mailServiceTest1() {
        try {
            mailService.sendEmail("cttony1997@126.com", "Tickets注册验证邮件", "您的注册码为989898");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }



}