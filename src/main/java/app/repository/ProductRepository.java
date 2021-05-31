package app.repository;

import app.entity.Product;

import java.util.List;

public interface ProductRepository {

    List<Product> getAll();

    Product save(Product product);

    Product getById(Long id);

    Product update(Product product);

    Product deleteById(Long id);

}
