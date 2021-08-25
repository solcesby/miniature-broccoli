package com.itechart.springproject.dto.email;

import lombok.Data;

import java.util.Set;

@Data
public class EmailRequest {

    private String subject;
    private String text;
    private Set<String> recipientsEmail;
    private String scheduleTime;
    private String senderEmail;

}
