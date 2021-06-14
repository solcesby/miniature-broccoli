package app.repository.impl;

import app.entity.product.Product;
import app.repository.JsonFileRepository;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository extends JsonFileRepository {

    public ProductRepository() {
        super(new File("src/main/resources/db/products.json"));
    }

    @Override
    protected List<Product> readJson() throws IOException {
        if (checkIfFileNotExistsAndEmpty()) {
            jsonFile.createNewFile();
            return new ArrayList<>();
        }
        return mapper.readValue(jsonFile, new TypeReference<>() {
        });
    }
}
