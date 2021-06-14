package app.service.impl;

import app.entity.product.Product;
import app.exception.ProductNotFoundException;
import app.repository.impl.ProductRepository;
import app.service.Service;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
public class ProductService implements Service<Product, Long> {

    private final ProductRepository productRepository = new ProductRepository();

    @Override
    public List<Product> getAll() {
        log.info("getting all products");
        return productRepository.getAll();
    }

    @Override
    public Product save(Product productToSave) {
        log.info("saving productToSave {}", productToSave);
        return productRepository.save(productToSave).orElseThrow();
    }

    @Override
    @SneakyThrows
    public Product getById(Long id) {
        log.info("getting product with id {}", id);
        return productRepository.getById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    public Product update(Product product) {
        log.info("updating product {}", product);
        return productRepository.update(product).orElseThrow();
    }

    @Override
    public void deleteById(Long id) {
        log.info("deleting product with id {}", id);
        productRepository.deleteById(id);
    }
}
