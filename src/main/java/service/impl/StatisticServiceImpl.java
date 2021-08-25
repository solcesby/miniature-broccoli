package service.impl;

import entity.customer.CustomerEntity;
import entity.order.OrderEntity;
import entity.orderdetails.OrderDetailsEntity;
import entity.product.ProductEntity;
import service.*;

import java.time.LocalDateTime;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StatisticServiceImpl implements StatisticService {

    private final CustomerService customerService = new CustomerServiceImpl();
    private final OrderService orderService = new OrderServiceImpl();
    private final OrderDetailsService orderDetailsService = new OrderDetailsServiceImpl();
    private final ProductService productService = new ProductServiceImpl();

    @Override
    public List<SimpleImmutableEntry<OrderEntity, List<OrderDetailsEntity>>> getAllOrdersInTheLastTwoWeeks() {
        final List<SimpleImmutableEntry<OrderEntity, List<OrderDetailsEntity>>> toReturn = new ArrayList<>();
        final List<OrderEntity> orders = orderService.getAllOrdersInTheLastTwoWeeks();

        orders.forEach(order -> {
            final List<OrderDetailsEntity> orderDetailsList = orderDetailsService.getByOrderId(order.getId());
            final SimpleImmutableEntry<OrderEntity, List<OrderDetailsEntity>> pair =
                    new SimpleImmutableEntry<>(order, orderDetailsList);
            toReturn.add(pair);
        });

        return toReturn;
    }

    @Override
    public SimpleImmutableEntry<CustomerEntity, List<OrderEntity>> getCustomerWithBiggestOrderHistoryInTheLastMonthBySumOfOrders() {
        final CustomerEntity customer = customerService.getWithBiggestSumOfOrders();
        final List<OrderEntity> orders = orderService.getByCustomerId(customer.getId());

        return new SimpleImmutableEntry<>(customer, orders);
    }

    @Override
    public SimpleImmutableEntry<CustomerEntity, List<OrderEntity>> getCustomerWithBiggestOrderHistoryInTheLastMonthByAverageTotalOfOrders() {
        final CustomerEntity customer = customerService.getWithBiggestAverageTotalOfOrders();
        final List<OrderEntity> orders = orderService.getByCustomerId(customer.getId());

        return new SimpleImmutableEntry<>(customer, orders);
    }

    @Override
    public SimpleImmutableEntry<CustomerEntity, List<OrderEntity>> getCustomerWithBiggestOrderHistoryInTheLastMonthByCountOfOrders() {
        final CustomerEntity customer = customerService.getWithBiggestCountOfOrders();
        final List<OrderEntity> orders = orderService.getByCustomerId(customer.getId());

        return new SimpleImmutableEntry<>(customer, orders);
    }

    @Override
    public ProductEntity getTheMostPopularProductInTheStoreFromDate(LocalDateTime date) {
        return productService.getTheMostPopularProductFromDate(date);
    }

    @Override
    public Long getCountOfOrdersWithProductFromDate(UUID productId, LocalDateTime date) {
        return orderDetailsService.getCountOfOrdersWithProductFromDate(productId, date);
    }

    @Override
    public Double getAveragePriceOfOrdersInTheLastMonth() {
        return orderService.getAveragePriceOfOrdersInTheLastMonth();
    }
}
