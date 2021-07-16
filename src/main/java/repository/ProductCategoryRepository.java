package repository;

import entity.productcategory.ProductCategoryEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductCategoryRepository {

    Optional<ProductCategoryEntity> get(UUID id);

    List<ProductCategoryEntity> getAll();

    Optional<ProductCategoryEntity> update(ProductCategoryEntity productCategory);

    Optional<ProductCategoryEntity> create(ProductCategoryEntity productCategory);

    void deleteById(UUID id);

}
