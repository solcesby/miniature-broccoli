package service;

import entity.customer.CustomerEntity;
import entity.order.OrderEntity;
import entity.orderdetails.OrderDetailsEntity;
import entity.product.ProductEntity;

import java.time.LocalDateTime;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.List;
import java.util.UUID;

public interface StatisticService {

    List<SimpleImmutableEntry<OrderEntity, List<OrderDetailsEntity>>> getAllOrdersInTheLastTwoWeeks();

    SimpleImmutableEntry<CustomerEntity, List<OrderEntity>> getCustomerWithBiggestOrderHistoryInTheLastMonthBySumOfOrders();

    SimpleImmutableEntry<CustomerEntity, List<OrderEntity>> getCustomerWithBiggestOrderHistoryInTheLastMonthByAverageTotalOfOrders();

    SimpleImmutableEntry<CustomerEntity, List<OrderEntity>> getCustomerWithBiggestOrderHistoryInTheLastMonthByCountOfOrders();

    ProductEntity getTheMostPopularProductInTheStoreFromDate(LocalDateTime date);

    Long getCountOfOrdersWithProductFromDate(UUID productId, LocalDateTime date);

    Double getAveragePriceOfOrdersInTheLastMonth();

}
