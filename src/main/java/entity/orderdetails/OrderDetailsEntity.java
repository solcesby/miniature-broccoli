package entity.orderdetails;

import entity.order.OrderEntity;
import entity.product.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.FetchType.LAZY;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_details")
public class OrderDetailsEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @MapsId
    @OneToOne(fetch = LAZY)
    private OrderEntity order;

    @Builder.Default
    @ManyToMany(cascade = {PERSIST, MERGE})
    @JoinTable(name = "order_details_product",
            joinColumns = @JoinColumn(name = "order_details_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<ProductEntity> products = new HashSet<>();

    @Column(name = "count")
    private Long count;

}
