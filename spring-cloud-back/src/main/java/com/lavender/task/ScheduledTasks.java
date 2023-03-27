package com.lavender.task;

import com.lavender.pojo.UserModel;
import com.lavender.utils.EmailRunnable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Slf4j
public class ScheduledTasks  {
    private UserModel userModel;

    @Resource
    private JavaMailSender javaMailSender;

    @Resource
    private TemplateEngine templateEngine;

    // 日期格式化类
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 30*60*1000)
    public void reportCurrentTime() {
        EmailRunnable emailRunnable = new EmailRunnable (userModel,javaMailSender,templateEngine);

        log.info("The time is now {} my system is restarting", DATE_FORMAT.format(new Date ()));
        emailRunnable.run ();
    }
}