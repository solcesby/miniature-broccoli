package com.itechart.springproject.entity.email;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Builder
@Table(name = "email")
@NoArgsConstructor
@AllArgsConstructor
public class EmailEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "email_address")
    private String emailAddress;

    @Column(name = "last_sent_at")
    private LocalDateTime lastSentAt;
}
