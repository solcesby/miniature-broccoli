package com.itechart.springproject.service.impl;

import com.itechart.springproject.config.quartz.job.SendEmailJob;
import com.itechart.springproject.dto.email.EmailRequest;
import com.itechart.springproject.service.EmailService;
import com.itechart.springproject.service.UserService;
import lombok.SneakyThrows;
import org.quartz.JobDataMap;
import org.quartz.Scheduler;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ScheduleServiceImpl {

    @SneakyThrows
    public void scheduleMailing(EmailRequest request, EmailService emailService, UserService userService) {
        JobDetailImpl jobDetail = new JobDetailImpl();
        jobDetail.setName(LocalDateTime.now().toString());
        jobDetail.setGroup("group");
        jobDetail.setJobClass(SendEmailJob.class);

        CronTriggerImpl cronTrigger = new CronTriggerImpl();
        cronTrigger.setName("|" + request.getSenderEmail() + "|" + request.getScheduleTime() + "|");
        cronTrigger.setCronExpression(request.getScheduleTime());

        JobDataMap jobDataMap = jobDetail.getJobDataMap();
        jobDataMap.put("recipientsEmails", request.getRecipientsEmail());
        jobDataMap.put("subject", request.getSubject());
        jobDataMap.put("text", request.getText());
        jobDataMap.put("senderEmail", request.getSenderEmail());
        jobDataMap.put("emailService", emailService);
        jobDataMap.put("userService", userService);

        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.start();
        scheduler.scheduleJob(jobDetail, cronTrigger);
    }
}
