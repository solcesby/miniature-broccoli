package app.repository.impl;

import app.entity.product.Product;
import app.repository.ProductRepository;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static app.mapper.ProductMapper.mapToEntity;
import static app.utils.ConnectionUtil.getConnection;

public class ProductRepositoryImpl implements ProductRepository {

    private static final String INSERT = "INSERT INTO products (name, price, description) " +
            "VALUES (?,?,?);";
    private static final String SELECT_ALL = "SELECT * FROM products;";
    private static final String SELECT_BY_ID = "SELECT * FROM products WHERE id = ?;";
    private static final String UPDATE = "UPDATE products " +
            "SET " +
            "name = ?, " +
            "price = ?, " +
            "description = ? " +
            "WHERE id = ?;";
    private static final String DELETE = "DELETE " +
            "FROM products " +
            "WHERE id = ?;";

    @Override
    @SneakyThrows
    public List<Product> getAll() {
        final List<Product> productList = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                productList.add(mapToEntity(rs));
            }
        }

        return productList;
    }

    @Override
    @SneakyThrows
    public Optional<Product> save(Product productToSave) {
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(INSERT)) {
            ps.setString(1, productToSave.getName());
            ps.setDouble(2, productToSave.getPrice());
            ps.setString(3, productToSave.getDescription());

            ps.execute();
        }

        return Optional.of(productToSave);
    }

    @Override
    @SneakyThrows
    public Optional<Product> getById(Long id) {
        Product product = null;

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_BY_ID)) {
            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    product = mapToEntity(rs);
                }
            }
        }

        return Optional.ofNullable(product);
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

            ps.execute();
        }
        return Optional.of(product);
    }

    @Override
    @SneakyThrows
    public void deleteById(Long id) {
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(DELETE)) {
            ps.setLong(1, id);

            ps.execute();
        }
    }
}
