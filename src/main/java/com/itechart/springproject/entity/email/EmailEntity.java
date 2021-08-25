package com.itechart.springproject.entity.email;

import com.itechart.springproject.entity.user.UserEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

import static javax.persistence.CascadeType.*;

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
    private String recipientEmailAddress;

    @Column(name = "last_sent_at")
    private LocalDateTime lastSentAt;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(cascade = {DETACH, MERGE, REFRESH})
    private UserEntity sender;
}
