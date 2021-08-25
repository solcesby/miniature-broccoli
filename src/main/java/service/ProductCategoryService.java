package service;

import entity.productcategory.ProductCategoryEntity;

import java.util.List;
import java.util.UUID;

public interface ProductCategoryService {

    ProductCategoryEntity get(UUID id);

    List<ProductCategoryEntity> getAll();

    ProductCategoryEntity update(ProductCategoryEntity productCategory);

    ProductCategoryEntity create(ProductCategoryEntity productCategory);

    void deleteById(UUID id);

}
