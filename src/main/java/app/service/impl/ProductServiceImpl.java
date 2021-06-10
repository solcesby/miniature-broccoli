package app.service.impl;

import app.entity.product.Product;
import app.exception.ProductNotFoundException;
import app.repository.ProductRepository;
import app.repository.impl.ProductRepositoryImpl;
import app.service.ProductService;
import lombok.SneakyThrows;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository = new ProductRepositoryImpl();

    @Override
    public List<Product> getAll() {
        return productRepository.getAll();
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product).orElseThrow();
    }

    @Override
    @SneakyThrows
    public Product getById(Long id) {
        return productRepository.getById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    public Product update(Product product) {
        return productRepository.update(product).orElseThrow();
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
