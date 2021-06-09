package app.repository.impl;

import app.entity.product.Product;
import app.repository.ProductRepository;
import lombok.SneakyThrows;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProductRepositoryImpl implements ProductRepository {

    private static final File PRODUCTS_FILE = new File("src/main/resources/db/products.json");
    private final ProductJsonFileRepository<Product> jsonFileRepository = new ProductJsonFileRepository<>(PRODUCTS_FILE);

    @Override
    @SneakyThrows
    public List<Product> getAll() {
        return jsonFileRepository.readJson();
    }

    @Override
    @SneakyThrows
    public Optional<Product> save(final Product productToSave) {
        final List<Product> productList = jsonFileRepository.readJson();
        final Long id = generateId(productList);

        productToSave.setId(id);

        productList.add(productToSave);

        jsonFileRepository.writeJson(productList);

        return Optional.of(productToSave);
    }

    @Override
    @SneakyThrows
    public Optional<Product> getById(final Long id) {
        final List<Product> productList = jsonFileRepository.readJson();
        final List<Product> foundProducts = productList.stream().filter(p -> id.equals(p.getId()))
                .collect(Collectors.toList());

        if (foundProducts.isEmpty()) {
            System.out.printf("Product with id: %s not found!%n", id);
            return Optional.empty();
        } else if (foundProducts.size() > 1) {
            System.out.printf("Product with id: %s found multiple times!%n", id);
            return Optional.empty();
        }

        return Optional.ofNullable(foundProducts.get(0));
    }

    @Override
    @SneakyThrows
    public Optional<Product> update(Product product) {
        final List<Product> productList = jsonFileRepository.readJson();
        final var updatedProduct = new Product(product);
        final int index = productList.indexOf(updatedProduct);

        productList.set(index, updatedProduct);

        jsonFileRepository.writeJson(productList);

        return Optional.of(updatedProduct);
    }

    @Override
    @SneakyThrows
    public Optional<Product> deleteById(Long id) {
        final List<Product> productList = jsonFileRepository.readJson();
        final List<Product> foundProducts = productList.stream().filter(p -> id.equals(p.getId()))
                .collect(Collectors.toList());

        if (foundProducts.isEmpty()) {
            System.out.printf("Product with id: %s not found!%n", id);
            return Optional.empty();
        } else if (foundProducts.size() > 1) {
            System.out.printf("Product with id: %s found multiple times!%n", id);
            return Optional.empty();
        }

        final var productToDelete = foundProducts.get(0);

        productList.remove(productToDelete);

        jsonFileRepository.writeJson(productList);

        return Optional.ofNullable(productToDelete);
    }

    private Long generateId(List<Product> productList) {
        if (productList.isEmpty()) {
            return 1L;
        } else {
            return productList.get(productList.size() - 1).getId() + 1L;
        }
    }
}
