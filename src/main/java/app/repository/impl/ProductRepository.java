package app.repository.impl;

import app.entity.product.Product;
import app.mapper.Mapper;
import app.mapper.ProductMapper;
import app.repository.Repository;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Optional;

import static app.utils.ConnectionUtil.getConnection;

@Log4j2
public class ProductRepository extends Repository<Product, Long> {

    private static final ProductMapper mapper = new ProductMapper();
    private static final String INSERT = "INSERT INTO products (name, price, description) " +
            "VALUES (?,?,?);";
    private static final String UPDATE = "UPDATE products " +
            "SET " +
            "name = ?, " +
            "price = ?, " +
            "description = ? " +
            "WHERE id = ?;";

    @Override
    @SneakyThrows
    public Optional<Product> save(Product productToSave) {
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(INSERT)) {
            ps.setString(1, productToSave.getName());
            ps.setDouble(2, productToSave.getPrice());
            ps.setString(3, productToSave.getDescription());

            ps.executeUpdate();
        }

        log.info("{} successfully saved", productToSave);
        return Optional.of(productToSave);
    }

    @Override
    @SneakyThrows
    public Optional<Product> update(Product product) {
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(UPDATE)) {
            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            ps.setString(3, product.getDescription());
            ps.setLong(4, product.getId());

            ps.executeUpdate();
        }

        log.info("{} successfully updated", product);
        return Optional.of(product);
    }

    @Override
    protected String getTableName() {
        return "products";
    }

    @Override
    protected Mapper<Product> getMapper() {
        return mapper;
    }
}
