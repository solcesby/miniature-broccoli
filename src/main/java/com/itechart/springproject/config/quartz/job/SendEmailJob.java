package com.itechart.springproject.config.quartz.job;

import com.itechart.springproject.service.EmailService;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@DisallowConcurrentExecution
public class SendEmailJob implements Job {

    @Override
    public void execute(JobExecutionContext context) {
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();

        //This is dumb solution, but I didn't find any other
        //Making field private final EmailService emailService don't work
        //Because this class is returned by SimpleJobFactory using method
        //Class.getInstance() and throws an error if there is any parameter in constructor
        //@Autowired don't work too, it just doesn't initiate EmailService, so it is always null
        EmailService emailService = (EmailService) jobDataMap.get("emailService");
        ((Set<String>) jobDataMap.get("recipientsEmails")).forEach(recipientEmail ->
                emailService.sendSimpleMessage(recipientEmail,
                        jobDataMap.getString("subject"),
                        jobDataMap.getString("text"),
                        jobDataMap.getString("senderEmail")));
    }
}

//        Cron expression
//        second, minute, hour, day of month, month, day(s) of week
//        * "0 0 * * * *" = the top of every hour of every day.
//        * "*/10 * * * * *" = every ten seconds.
//        * "0 0 8-10 * * *" = 8, 9 and 10 o'clock of every day.
//        * "0 0 8,10 * * *" = 8 and 10 o'clock of every day.
//        * "0 0/30 8-10 * * *" = 8:00, 8:30, 9:00, 9:30 and 10 o'clock every day.
//        * "0 0 9-17 * * MON-FRI" = on the hour nine-to-five weekdays
//        * "0 0 0 25 12 ?" = every Christmas Day at midnight