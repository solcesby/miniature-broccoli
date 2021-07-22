package com.itechart.springproject.entity;

import java.time.LocalDateTime;
import java.util.UUID;

public class EmailEntity {

    private UUID id;

    private String emailAddress;

    private LocalDateTime lastSentAt;
}
