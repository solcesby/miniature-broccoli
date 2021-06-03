package app.service;

import app.entity.product.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAll();

    Product save(Product product);

    Product getById(Long id);

    Product update(Product product);

    Product deleteById(Long id);

}
