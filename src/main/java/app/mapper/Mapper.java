package app.mapper;

import java.sql.ResultSet;

public interface Mapper<T> {

    T mapToEntity(ResultSet rs);
}
