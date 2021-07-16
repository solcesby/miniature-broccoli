package repository;


import entity.orderdetails.OrderDetailsEntity;
import entity.product.ProductEntity;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderDetailsRepository {

    Optional<OrderDetailsEntity> get(UUID id);

    List<OrderDetailsEntity> getAll();

    List<OrderDetailsEntity> getByOrderId(UUID id);

    Optional<Long> getCountOfOrdersWithProductFromDate(UUID productId, Date date);

    Optional<OrderDetailsEntity> update(OrderDetailsEntity orderDetailsEntity);

    Optional<OrderDetailsEntity> create(OrderDetailsEntity orderDetails);

    void deleteById(UUID id);

}
