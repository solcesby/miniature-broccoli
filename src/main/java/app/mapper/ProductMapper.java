package app.mapper;

import app.entity.product.Product;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

import java.sql.ResultSet;

@Log4j2
public class ProductMapper implements Mapper<Product> {

    @Override
    @SneakyThrows
    public Product mapToEntity(ResultSet rs) {
        log.info("mapping result set of {}", rs);
        return Product.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .price(rs.getDouble("price"))
                .description(rs.getString("description"))
                .build();
    }

}
