package service.impl;

import entity.orderdetails.OrderDetailsEntity;
import repository.OrderDetailsRepository;
import repository.impl.OrderDetailsRepositoryImpl;
import service.OrderDetailsService;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class OrderDetailsServiceImpl implements OrderDetailsService {

    private final OrderDetailsRepository orderDetailsRepository = new OrderDetailsRepositoryImpl();

    @Override
    public OrderDetailsEntity get(UUID id) {
        return orderDetailsRepository.get(id).orElseThrow();
    }

    @Override
    public List<OrderDetailsEntity> getAll() {
        return orderDetailsRepository.getAll();
    }

    @Override
    public List<OrderDetailsEntity> getByOrderId(UUID id) {
        return orderDetailsRepository.getByOrderId(id);
    }

    @Override
    public Long getCountOfOrdersWithProductFromDate(UUID productId, Date date) {
        return orderDetailsRepository.getCountOfOrdersWithProductFromDate(productId, date).orElseThrow();
    }

    @Override
    public OrderDetailsEntity update(OrderDetailsEntity orderDetails) {
        return orderDetailsRepository.update(orderDetails).orElseThrow();
    }

    @Override
    public OrderDetailsEntity create(OrderDetailsEntity orderDetails) {
        return orderDetailsRepository.create(orderDetails).orElseThrow();
    }

    @Override
    public void deleteById(UUID id) {
        orderDetailsRepository.deleteById(id);
    }
}
