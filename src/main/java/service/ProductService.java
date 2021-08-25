package service;

import entity.product.ProductEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ProductService {

    ProductEntity get(UUID id);

    List<ProductEntity> getAll();

    ProductEntity getTheMostPopularProductFromDate(LocalDateTime date);

    ProductEntity update(ProductEntity product);

    ProductEntity create(ProductEntity product);

    void deleteById(UUID id);

}
