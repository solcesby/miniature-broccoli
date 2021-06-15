package app.repository;

import app.mapper.Mapper;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static app.utils.ConnectionUtil.getConnection;


@Log4j2
public abstract class Repository<T, ID> {
    private final String selectAll = String.format("SELECT * FROM %s;", getTableName());
    private final String selectById = String.format("SELECT * FROM %s WHERE id = ?;", getTableName());
    private final String delete = String.format("DELETE " +
            "FROM %s " +
            "WHERE id = ?;", getTableName());

    @SneakyThrows
    public List<T> getAll() {
        final List<T> list = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(selectAll);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(getMapper().mapToEntity(rs));
            }
        }
        log.info("{} elements were found", list.size());
        return list;
    }

    public abstract Optional<T> save(T userToSave);

    @SneakyThrows
    public Optional<T> getById(ID id) {
        T found = null;

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(selectById)) {
            ps.setLong(1, (Long) id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    found = getMapper().mapToEntity(rs);
                }
            }
        }
        log.info("found {} by id {}", found, id);
        return Optional.ofNullable(found);
    }

    public abstract Optional<T> update(T user);

    @SneakyThrows
    public void deleteById(ID id) {
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(delete)) {
            ps.setLong(1, (Long) id);

            ps.executeUpdate();
        }
        log.info("element with id {} deleted from table '{}'", id, getTableName());
    }

    protected String getTableName() {
        return null;
    }

    protected abstract Mapper<T> getMapper();
}
