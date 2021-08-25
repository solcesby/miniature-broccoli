package service.impl;

import entity.order.OrderEntity;
import repository.OrderRepository;
import repository.impl.OrderRepositoryImpl;
import service.OrderService;

import java.util.List;
import java.util.UUID;

public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository = new OrderRepositoryImpl();

    @Override
    public OrderEntity get(UUID id) {
        return orderRepository.get(id).orElseThrow();
    }

    @Override
    public List<OrderEntity> getAll() {
        return orderRepository.getAll();
    }

    @Override
    public List<OrderEntity> getByCustomerId(UUID id) {
        return orderRepository.getByCustomerId(id);
    }

    @Override
    public List<OrderEntity> getAllOrdersInTheLastTwoWeeks() {
        return orderRepository.getAllOrdersInTheLastTwoWeeks();
    }

    @Override
    public Double getAveragePriceOfOrdersInTheLastMonth() {
        return orderRepository.getAveragePriceOfOrdersInTheLastMonth().orElseThrow();
    }

    @Override
    public OrderEntity update(OrderEntity order) {
        return orderRepository.update(order).orElseThrow();
    }

    @Override
    public OrderEntity create(OrderEntity order) {
        return orderRepository.create(order).orElseThrow();
    }

    @Override
    public void deleteById(UUID id) {
        orderRepository.deleteById(id);
    }
}
