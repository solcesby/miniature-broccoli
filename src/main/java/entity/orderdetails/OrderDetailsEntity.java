package entity.orderdetails;

import entity.order.OrderEntity;
import entity.product.ProductEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

@Data
@Entity
@Table(name = "order_details")
public class OrderDetailsEntity {

    @Id
    private UUID id;

    @MapsId
    @OneToOne(fetch = LAZY, cascade = ALL)
    private OrderEntity order;

    @ManyToMany(cascade = ALL)
    @JoinTable(name = "order_details_product",
            joinColumns = @JoinColumn(name = "order_details_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<ProductEntity> products;

    @Column(name = "count")
    private Long count;

}
