package app.repository;

import app.entity.product.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    List<Product> getAll();

    Optional<Product> save(Product productToSave);

    Optional<Product> getById(Long id);

    Optional<Product> update(Product product);

    void deleteById(Long id);

}
