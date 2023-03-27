package com.lavender.utils;

import com.lavender.pojo.ResultModel;
import com.lavender.pojo.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * 重新开启一个线程单独发送邮件任务
 */


public class EmailRunnable implements Runnable{

    private UserModel userModel;

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private TemplateEngine templateEngine;

    // 带参构造器
    public EmailRunnable(UserModel userModel,JavaMailSender javaMailSender,
                         TemplateEngine templateEngine){

        this.userModel=userModel;
        this.javaMailSender=javaMailSender;
        this.templateEngine=templateEngine;
    }

    public EmailRunnable() {

    }

//    public EmailRunnable(UserModel userModel) {
//        this.userModel=userModel;
//    }

    @Override
    public void run() {

        try{
            MimeMessage message = javaMailSender.createMimeMessage ();
            MimeMessageHelper helper = new MimeMessageHelper (message,true);
//            helper.setTo (userModel.getEmail ());
            helper.setTo ("319246676@qq.com");
//            helper.setTo ("619066412@qq.com");

//            helper.setTo ("2368160668@qq.com");
            helper.setFrom ("319246676@qq.com");
            helper.setSubject ("NOTIFICATION");
            Context context = new Context ();
            context.setVariable ("name","阿伟");

            String mail = templateEngine.process ("email.html", context);
            helper.setText (mail,true);
            javaMailSender.send (message);


        } catch (MessagingException e) {
            System.out.println ("发送失败");
            e.printStackTrace ();
        }

    }


}
