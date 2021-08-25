package entity.order;

import entity.customer.CustomerEntity;
import entity.order.enums.OrderStatusEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.LAZY;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "`order`")
public class OrderEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "order_number")
    private Long orderNumber;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = LAZY)
    private CustomerEntity customer;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "delivery_address")
    private String deliveryAddress;

    @Enumerated(STRING)
    @Column(name = "status")
    private OrderStatusEntity status;

}
