package app.mapper;

import app.entity.user.User;
import app.entity.user.enums.Role;
import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.util.ArrayList;

public class UserMapper {

    @SneakyThrows
    public static User mapToEntity(ResultSet rs) {
        return User.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .lastName(rs.getString("last_name"))
                .role(Role.valueOf(rs.getString("role")))
                .email(rs.getString("email"))
                .password(rs.getString("password"))
                .basket(new ArrayList<>())
                .build();
    }
}
