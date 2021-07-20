package service.impl;

import entity.product.ProductEntity;
import repository.ProductRepository;
import repository.impl.ProductRepositoryImpl;
import service.ProductService;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository = new ProductRepositoryImpl();

    @Override
    public ProductEntity get(UUID id) {
        return productRepository.get(id).orElseThrow();
    }

    @Override
    public List<ProductEntity> getAll() {
        return productRepository.getAll();
    }

    @Override
    public ProductEntity getTheMostPopularProductFromDate(LocalDateTime date) {
        return productRepository.getTheMostPopularProductFromDate(date).orElseThrow();
    }

    @Override
    public ProductEntity update(ProductEntity product) {
        return productRepository.update(product).orElseThrow();
    }

    @Override
    public ProductEntity create(ProductEntity product) {
        return productRepository.create(product).orElseThrow();
    }

    @Override
    public void deleteById(UUID id) {
        productRepository.deleteById(id);
    }
}
