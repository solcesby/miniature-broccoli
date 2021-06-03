package app.repository.impl;

import app.entity.product.Product;
import app.repository.ProductRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ProductRepositoryImpl implements ProductRepository {

    private final ObjectMapper mapper = new ObjectMapper();
    private static final File PRODUCTS_FILE = new File("src/main/resources/db/products.json");

    public ProductRepositoryImpl() {
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
    }

    @Override
    @SneakyThrows
    public List<Product> getAll() {
        return readJson();
    }

    @Override
    @SneakyThrows
    public Product save(final Product productToSave) {
        if (!PRODUCTS_FILE.exists()) {
            var ignored = PRODUCTS_FILE.createNewFile();
        } else if (PRODUCTS_FILE.length() == 0) {
            productToSave.setId(1L);

            mapper.writerWithDefaultPrettyPrinter().writeValue(PRODUCTS_FILE, productToSave);
        } else {
            final List<Product> productList = readJson();

            productToSave.setId(productList.get(productList.size() - 1).getId() + 1L);

            productList.add(productToSave);

            mapper.writerWithDefaultPrettyPrinter().writeValue(PRODUCTS_FILE, productList);
        }
        return productToSave;
    }

    @Override
    @SneakyThrows
    public Product getById(final Long id) {
        final List<Product> productList = readJson();
        final List<Product> foundProducts = productList.stream().filter(p -> id.equals(p.getId()))
                .collect(Collectors.toList());

        if (foundProducts.size() == 0) {
            System.out.printf("Product with id: %s not found!%n", id);
            return null;
        } else if (foundProducts.size() > 1) {
            System.out.printf("Product with id: %s found multiple times!%n", id);
        }

        return foundProducts.get(0);
    }

    @Override
    @SneakyThrows
    public Product update(Product product) {
        final List<Product> productList = readJson();
        final Product updatedProduct = getById(product.getId());
        final int index = productList.indexOf(updatedProduct);

        updatedProduct.setName(product.getName());
        updatedProduct.setDescription(product.getDescription());
        updatedProduct.setPrice(product.getPrice());

        productList.set(index, updatedProduct);

        mapper.writerWithDefaultPrettyPrinter().writeValue(PRODUCTS_FILE, productList);

        return updatedProduct;
    }

    @Override
    @SneakyThrows
    public Product deleteById(Long id) {
        final List<Product> productList = readJson();
        final Product productToDelete = getById(id);

        productList.remove(productToDelete);

        mapper.writerWithDefaultPrettyPrinter().writeValue(PRODUCTS_FILE, productList);

        return productToDelete;
    }

    private void checkIfFileExistsAndNotEmpty() throws IOException {
        if (!PRODUCTS_FILE.exists()) {
            var ignored = PRODUCTS_FILE.createNewFile();
        } else if (PRODUCTS_FILE.length() == 0) {
            throw new IOException();
        }
    }

    private List<Product> readJson() throws IOException {
        checkIfFileExistsAndNotEmpty();

        final Product[] productArray = mapper.readValue(PRODUCTS_FILE, Product[].class);
        return Arrays.stream(productArray).collect(Collectors.toList());
    }
}
