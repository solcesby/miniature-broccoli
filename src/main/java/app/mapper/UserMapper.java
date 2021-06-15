package app.mapper;

import app.entity.user.User;
import app.entity.user.enums.Role;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

import java.sql.ResultSet;
import java.util.ArrayList;

@Log4j2
public class UserMapper implements Mapper<User> {

    @Override
    @SneakyThrows
    public User mapToEntity(ResultSet rs) {
        log.info("mapping result set of {}", rs);
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
