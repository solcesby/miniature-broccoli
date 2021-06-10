package app.service.impl;

import app.entity.product.Product;
import app.exception.ProductNotFoundException;
import app.repository.impl.ProductRepository;
import app.service.ProductService;
import lombok.SneakyThrows;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl() {
        this.productRepository = new ProductRepository();
    }

    @Override
    @SneakyThrows
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
        return (Product) productRepository.getById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    public Product update(Product product) {
        return productRepository.update(product).orElseThrow();
    }

    @Override
    @SneakyThrows
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
