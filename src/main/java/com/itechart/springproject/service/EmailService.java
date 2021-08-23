package com.itechart.springproject.service;

import com.itechart.springproject.dto.user.UserDTO;

public interface EmailService {

    void sendSimpleMessage(String to, String subject, String text, String senderEmail);

    void sendSimpleMessage(String to, String subject, String text);

}
