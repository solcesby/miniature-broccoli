package service;

import entity.orderdetails.OrderDetailsEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface OrderDetailsService {

    OrderDetailsEntity get(UUID id);

    List<OrderDetailsEntity> getAll();

    List<OrderDetailsEntity> getByOrderId(UUID id);

    Long getCountOfOrdersWithProductFromDate(UUID productId, LocalDateTime date);

    OrderDetailsEntity update(OrderDetailsEntity orderDetails);

    OrderDetailsEntity create(OrderDetailsEntity orderDetails);

    void deleteById(UUID id);

}
