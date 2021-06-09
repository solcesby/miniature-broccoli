package app.repository.impl;

import app.entity.product.Product;
import app.repository.AbstractJsonFileRepository;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductJsonFileRepository<T> extends AbstractJsonFileRepository<Product> {
    public ProductJsonFileRepository(File jsonFile) {
        super(jsonFile);
    }

    @Override
    public List<Product> readJson() throws IOException {
        if (checkIfFileNotExistsAndEmpty()) {
            jsonFile.createNewFile();
            return new ArrayList<>();
        }
        return mapper.readValue(jsonFile, new TypeReference<>() {
        });
    }
}
