package app.service.impl;

import app.entity.product.Product;
import app.exception.ProductNotFoundException;
import app.repository.ProductRepository;
import app.repository.impl.ProductRepositoryImpl;
import app.service.ProductService;
import lombok.SneakyThrows;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl() {
        this.productRepository = new ProductRepositoryImpl();
    }

    @Override
    @SneakyThrows
    public List<Product> getAll() {
        return productRepository.getAll().orElseThrow(ProductNotFoundException::new);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product).get();
    }

    @Override
    @SneakyThrows
    public Product getById(Long id) {
        return productRepository.getById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    public Product update(Product product) {
        return productRepository.update(product).get();
    }

    @Override
    @SneakyThrows
    public Product deleteById(Long id) {
        return productRepository.deleteById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }
}
