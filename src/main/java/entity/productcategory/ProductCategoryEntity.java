package entity.productcategory;

import entity.product.ProductEntity;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product_category")
public class ProductCategoryEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "category_name")
    private String categoryName;

    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy = "categories")
    private Set<ProductEntity> products = new HashSet<>();
}
