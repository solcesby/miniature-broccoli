package repository;

import entity.order.OrderEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository {

    Optional<OrderEntity> get(UUID id);

    List<OrderEntity> getAll();

    List<OrderEntity> getByCustomerId(UUID id);

    List<OrderEntity> getAllOrdersInTheLastTwoWeeks();

    Optional<Double> getAveragePriceOfOrdersInTheLastMonth();

    Optional<OrderEntity> update(OrderEntity order);

    Optional<OrderEntity> create(OrderEntity order);

    void deleteById(UUID id);

}
