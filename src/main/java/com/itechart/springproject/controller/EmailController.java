package com.itechart.springproject.controller;

import com.itechart.springproject.dto.email.EmailRequest;
import com.itechart.springproject.service.EmailService;
import com.itechart.springproject.service.UserService;
import com.itechart.springproject.service.impl.ScheduleServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/email")
public class EmailController {

    private final UserService userService;
    private final EmailService emailService;
    private final ScheduleServiceImpl scheduleService;

    @PostMapping
    public ResponseEntity<String> sendEmail(@Valid @RequestBody final EmailRequest request) {

        scheduleService.scheduleMailing(request, emailService, userService);

        return new ResponseEntity<>("Well, it should be sent", OK);
    }

}

