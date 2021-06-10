package app.mapper;

import app.entity.product.Product;
import lombok.SneakyThrows;

import java.sql.ResultSet;

public class ProductMapper {

    @SneakyThrows
    public static Product mapToEntity(ResultSet rs) {
        return Product.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .price(rs.getDouble("price"))
                .description(rs.getString("description"))
                .build();
    }

}
