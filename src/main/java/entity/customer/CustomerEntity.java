package entity.customer;

import entity.customer.enums.CustomerGenderEntity;
import entity.order.OrderEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.EnumType.STRING;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer")
public class CustomerEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Enumerated(STRING)
    @Column(name = "gender")
    private CustomerGenderEntity gender;

    @Column(name = "birth_date")
    private Date birthDate;

    @Builder.Default
    @OneToMany(mappedBy = "customer", cascade = ALL, orphanRemoval = true)
    private Set<OrderEntity> orders = new HashSet<>();

}
