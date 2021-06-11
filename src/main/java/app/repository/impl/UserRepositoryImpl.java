package app.repository.impl;

import app.entity.user.User;
import app.repository.UserRepository;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static app.mapper.UserMapper.mapToEntity;
import static app.utils.ConnectionUtil.getConnection;
import static java.sql.Statement.RETURN_GENERATED_KEYS;

@Log4j2
public class UserRepositoryImpl implements UserRepository {

    private static final String INSERT = "INSERT INTO users (name, last_name, role, email, password) " +
            "VALUES (?,?,?,?,?);";
    private static final String SELECT_ALL = "SELECT * FROM users;";
    private static final String SELECT_BY_ID = "SELECT * FROM users WHERE id = ?;";
    private static final String SELECT_BY_EMAIL = "SELECT * FROM users WHERE email = ?;";
    private static final String UPDATE = "UPDATE users " +
            "SET " +
            "name = ?, " +
            "last_name = ?, " +
            "role = ?, " +
            "email = ?, " +
            "password = ? " +
            "WHERE id = ?;";
    private static final String DELETE = "DELETE " +
            "FROM users " +
            "WHERE id = ?;";

    @Override
    @SneakyThrows
    public List<User> getAll() {
        final List<User> userList = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                userList.add(mapToEntity(rs));
            }
        }

        log.info("{} users were found", userList.size());
        return userList;
    }

    @Override
    @SneakyThrows
    public Optional<User> save(User userToSave) {
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(INSERT, RETURN_GENERATED_KEYS)) {
            ps.setString(1, userToSave.getName());
            ps.setString(2, userToSave.getLastName());
            ps.setString(3, userToSave.getRole().name());
            ps.setString(4, userToSave.getEmail());
            ps.setString(5, userToSave.getPassword());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    userToSave.setId(rs.getLong("id"));
                }
            }
        }

        log.info("{} successfully saved", userToSave);
        return Optional.of(userToSave);
    }

    @Override
    @SneakyThrows
    public Optional<User> getById(Long id) {
        User user = null;

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_BY_ID)) {
            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    user = mapToEntity(rs);
                }
            }
        }

        log.info("found {} by id {}", user, id);
        return Optional.ofNullable(user);
    }

    @Override
    @SneakyThrows
    public Optional<User> getByEmail(String email) {
        User user = null;

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_BY_EMAIL)) {
            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    user = mapToEntity(rs);
                }
            }
        }

        log.info("found {} by email {}", user, email);
        return Optional.ofNullable(user);
    }

    @Override
    @SneakyThrows
    public Optional<User> update(User user) {
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(UPDATE)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getLastName());
            ps.setObject(3, user.getRole());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getPassword());
            ps.setLong(6, user.getId());

            ps.executeUpdate();
        }

        log.info("{} successfully updated", user);
        return Optional.of(user);
    }

    @Override
    @SneakyThrows
    public void deleteById(Long id) {
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(DELETE)) {
            ps.setLong(1, id);

            ps.executeUpdate();
        }
        log.info("user with id {} deleted", id);
    }
}
