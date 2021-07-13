package entity.product;

import entity.orderdetails.OrderDetailsEntity;
import entity.productcategory.ProductCategoryEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private UUID id;

    @Column(name = "product_name")
    private String productName;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<ProductCategoryEntity> categories;

    @Column(name = "price")
    private Double price;

    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "products")
    private List<OrderDetailsEntity> orderDetails;

}
