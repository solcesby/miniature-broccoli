package app.repository;

import app.entity.product.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    Optional<List<Product>> getAll();

    Optional<Product> save(Product product);

    Optional<Product> getById(Long id);

    Optional<Product> update(Product product);

    Optional<Product> deleteById(Long id);

}
