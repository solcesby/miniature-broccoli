package repository;

import entity.product.ProductEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository {

    Optional<ProductEntity> get(UUID id);

    List<ProductEntity> getAll();

    Optional<ProductEntity> getTheMostPopularProductFromDate(LocalDateTime date);

    Optional<ProductEntity> update(ProductEntity product);

    Optional<ProductEntity> create(ProductEntity product);

    void deleteById(UUID id);
}