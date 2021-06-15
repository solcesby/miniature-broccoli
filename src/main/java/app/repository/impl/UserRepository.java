package app.repository.impl;

import app.entity.user.User;
import app.mapper.Mapper;
import app.mapper.UserMapper;
import app.repository.Repository;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

import static app.utils.ConnectionUtil.getConnection;
import static java.sql.Statement.RETURN_GENERATED_KEYS;

@Log4j2
public class UserRepository extends Repository<User, Long> {

    private static final UserMapper mapper = new UserMapper();
    private static final String INSERT = "INSERT INTO users (name, last_name, role, email, password) " +
            "VALUES (?,?,?,?,?);";
    private static final String SELECT_BY_EMAIL = "SELECT * FROM users WHERE email = ?;";
    private static final String UPDATE = "UPDATE users " +
            "SET " +
            "name = ?, " +
            "last_name = ?, " +
            "role = ?, " +
            "email = ?, " +
            "password = ? " +
            "WHERE id = ?;";

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

    @SneakyThrows
    public Optional<User> getByEmail(String email) {
        User user = null;

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_BY_EMAIL)) {
            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    user = mapper.mapToEntity(rs);
                }
            }
        }

        log.info("found {} by email {}", user, email);
        return Optional.ofNullable(user);
    }

    @Override
    protected String getTableName() {
        return "users";
    }

    @Override
    protected Mapper<User> getMapper() {
        return mapper;
    }
}
