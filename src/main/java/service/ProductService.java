package service;

import entity.product.ProductEntity;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductService {

    ProductEntity get(UUID id);

    List<ProductEntity> getAll();

    ProductEntity getTheMostPopularProductFromDate(Date date);

    ProductEntity update(ProductEntity product);

    ProductEntity create(ProductEntity product);

    void deleteById(UUID id);

}
