package com.itechart.springproject.service.impl;

import com.itechart.springproject.entity.email.EmailEntity;
import com.itechart.springproject.mapper.UserMapper;
import com.itechart.springproject.repository.EmailRepository;
import com.itechart.springproject.repository.UserRepository;
import com.itechart.springproject.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final EmailRepository emailRepository;
    private final JavaMailSender mailSender;
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final static String NO_REPLY_ADDRESS = "noreply@solcesby.com";

    @Override
    @SneakyThrows
    public void sendSimpleMessage(final String to, final String subject, final String text, final String senderEmail) {
        final EmailEntity emailEntity = linkSenderToEmail(to, senderEmail);
        final SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(to);
        message.setFrom(senderEmail);
        message.setSubject(subject);
        message.setText(text);

        mailSender.send(message);
        emailRepository.save(emailEntity);
    }

    @Override
    @SneakyThrows
    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setFrom(NO_REPLY_ADDRESS);
        message.setSubject(subject);
        message.setText(text);

        mailSender.send(message);
    }

    private EmailEntity linkSenderToEmail(String email, String senderEmail) {
        return EmailEntity.builder()
                .recipientEmailAddress(email)
                .lastSentAt(LocalDateTime.now())
                .sender(userRepository.findByEmail(senderEmail).orElseThrow(() ->
                        new EntityNotFoundException(format("User with email: %s not found", email))))
                .build();
    }
}
