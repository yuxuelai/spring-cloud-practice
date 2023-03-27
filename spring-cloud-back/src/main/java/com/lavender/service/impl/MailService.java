package com.lavender.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Component
public class MailService {

    @Resource
    JavaMailSender javaMailSender;

    public void sendSimpleMail(String from, String to, String cc,
                               String subject, String content){

        SimpleMailMessage message = new SimpleMailMessage ();

        message.setFrom (from);
        message.setTo (to);
        message.setCc (cc);
        message.setSubject (subject);
        message.setText (content);
        javaMailSender.send (message);


    }

    public void sendAttachFileMail(String from,String to,String subject,String content,
                                File file){
        try{

            MimeMessage message = javaMailSender.createMimeMessage ();
            MimeMessageHelper helper = new MimeMessageHelper (message,true);
            helper.setFrom (from);
            helper.setTo (to);
            helper.setSubject (subject);
            helper.setText (content);
            helper.addAttachment (file.getName (),file);
            javaMailSender.send (message);
        } catch (MessagingException e) {
            e.printStackTrace ();
        }

    }
}
