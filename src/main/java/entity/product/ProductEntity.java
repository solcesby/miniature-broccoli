package entity.product;

import entity.orderdetails.OrderDetailsEntity;
import entity.productcategory.ProductCategoryEntity;
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

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "product_name")
    private String productName;

    @Builder.Default
    @ManyToMany(cascade = {PERSIST, MERGE})
    @JoinTable(name = "product_product_category",
            joinColumns = @JoinColumn(name = "product_category_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<ProductCategoryEntity> categories = new HashSet<>();

    @Column(name = "price")
    private Double price;

    @Column(name = "description")
    private String description;

    @Builder.Default
    @ManyToMany(mappedBy = "products")
    private Set<OrderDetailsEntity> orderDetails = new HashSet<>();

}
