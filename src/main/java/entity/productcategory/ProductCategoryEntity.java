package entity.productcategory;

import entity.product.ProductEntity;
import lombok.Data;

import javax.persistence.*;

import java.util.Set;
import java.util.UUID;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "product_category")
public class ProductCategoryEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private UUID id;

    @Column(name = "category_name")
    private String categoryName;

    @ManyToMany(mappedBy = "categories")
    private Set<ProductEntity> products;
}
