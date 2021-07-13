package entity.order;

import entity.customer.CustomerEntity;
import entity.order.enums.OrderStatusEntity;
import entity.orderdetails.OrderDetailsEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "order")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private UUID id;

    @Column(name = "order_number")
    private Long orderNumber;

    @JoinColumn(name = "customer_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CustomerEntity customer;

    @Column(name = "order_date")
    private Date orderDate;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "delivery_address")
    private String deliveryAddress;

    @Enumerated(STRING)
    @Column(name = "status")
    private OrderStatusEntity status;

}
