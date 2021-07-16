package service;

import entity.order.OrderEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderService {

    OrderEntity get(UUID id);

    List<OrderEntity> getAll();

    List<OrderEntity> getByCustomerId(UUID id);

    List<OrderEntity> getAllOrdersInTheLastTwoWeeks();

    Double getAveragePriceOfOrdersInTheLastMonth();

    OrderEntity update(OrderEntity order);

    OrderEntity create(OrderEntity order);

    void deleteById(UUID id);

}
