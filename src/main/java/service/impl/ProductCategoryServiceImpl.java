package service.impl;

import entity.productcategory.ProductCategoryEntity;
import repository.ProductCategoryRepository;
import repository.impl.ProductCategoryRepositoryImpl;
import service.ProductCategoryService;

import java.util.List;
import java.util.UUID;

public class ProductCategoryServiceImpl implements ProductCategoryService {

    private final ProductCategoryRepository productCategoryRepository = new ProductCategoryRepositoryImpl();

    @Override
    public ProductCategoryEntity get(UUID id) {
        return productCategoryRepository.get(id).orElseThrow();
    }

    @Override
    public List<ProductCategoryEntity> getAll() {
        return productCategoryRepository.getAll();
    }

    @Override
    public ProductCategoryEntity update(ProductCategoryEntity productCategory) {
        return productCategoryRepository.update(productCategory).orElseThrow();
    }

    @Override
    public ProductCategoryEntity create(ProductCategoryEntity productCategory) {
        return productCategoryRepository.create(productCategory).orElseThrow();
    }

    @Override
    public void deleteById(UUID id) {
        productCategoryRepository.deleteById(id);
    }
}
