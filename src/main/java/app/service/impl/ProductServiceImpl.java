package app.service.impl;

import app.entity.product.Product;
import app.repository.ProductRepository;
import app.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    @Transactional
    public List<Product> getAll() {
        return null;
    }

    @Override
    @Transactional
    public Product save(Product product) {
        return null;
    }

    @Override
    @Transactional
    public Product getById(Long id) {
        return null;
    }

    @Override
    @Transactional
    public Product update(Product product) {
        return null;
    }

    @Override
    @Transactional
    public Product deleteById(Long id) {
        return null;
    }
}
