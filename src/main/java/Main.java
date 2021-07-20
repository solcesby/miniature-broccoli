import com.github.javafaker.Faker;
import entity.customer.CustomerEntity;
import entity.customer.enums.CustomerGenderEntity;
import entity.order.OrderEntity;
import entity.order.enums.OrderStatusEntity;
import entity.orderdetails.OrderDetailsEntity;
import entity.product.ProductEntity;
import entity.productcategory.ProductCategoryEntity;
import service.CustomerService;
import service.OrderDetailsService;
import service.StatisticService;
import service.impl.CustomerServiceImpl;
import service.impl.OrderDetailsServiceImpl;
import service.impl.StatisticServiceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import static entity.customer.enums.CustomerGenderEntity.FEMALE;
import static entity.customer.enums.CustomerGenderEntity.MALE;
import static entity.order.enums.OrderStatusEntity.*;

public class Main {
    public static void main(String[] args) {
        Faker faker = new Faker();
        CustomerService customerService = new CustomerServiceImpl();
        OrderDetailsService orderDetailsService = new OrderDetailsServiceImpl();

        UUID productId = UUID.randomUUID();

        for (int i = 0; i < 10; i++) {
            Long count = faker.number().numberBetween(1L, 50L);

            ProductCategoryEntity productCategory = ProductCategoryEntity.builder()
                    .categoryName(faker.pokemon().name())
                    .build();

            CustomerEntity customer = CustomerEntity.builder()
                    .firstName(faker.name().firstName())
                    .lastName(faker.name().lastName())
                    .email(faker.internet().emailAddress())
                    .address(faker.address().streetAddress())
                    .birthDate(LocalDateTime.now())
                    .gender(generateGender())
                    .build();

            ProductEntity product = ProductEntity.builder()
                    .productName(faker.funnyName().name())
                    .categories(Set.of(productCategory))
                    .price(faker.number().randomDouble(2, 1, 100))
                    .description(faker.lorem().sentence())
                    .build();


            OrderEntity order = OrderEntity.builder()
                    .orderNumber(new Random().nextLong())
                    .customer(customer)
                    .orderDate(LocalDate.of(2021, 6, 25).atTime(LocalTime.now()))
                    .totalPrice(Double.parseDouble(String.format("%.2f", count * product.getPrice())))
                    .deliveryAddress(customer.getAddress())
                    .status(generateStatus())
                    .build();


            OrderDetailsEntity orderDetails = OrderDetailsEntity.builder()
                    .order(order)
                    .products(Set.of(product))
                    .count(count)
                    .build();

            customerService.create(customer);
            orderDetailsService.create(orderDetails);
            productId = product.getId();
        }

        StatisticService service = new StatisticServiceImpl();

        System.out.printf("getAllOrdersInTheLastTwoWeeks%n%s%n", service.getAllOrdersInTheLastTwoWeeks());
        System.out.printf("getCustomerWithBiggestOrderHistoryInTheLastMonthBySumOfOrders%n%s%n", service.getCustomerWithBiggestOrderHistoryInTheLastMonthBySumOfOrders());
        System.out.printf("getCustomerWithBiggestOrderHistoryInTheLastMonthByAverageTotalOfOrders%n%s%n", service.getCustomerWithBiggestOrderHistoryInTheLastMonthByAverageTotalOfOrders());
        System.out.printf("getCustomerWithBiggestOrderHistoryInTheLastMonthByCountOfOrders%n%s%n", service.getCustomerWithBiggestOrderHistoryInTheLastMonthByCountOfOrders());
        System.out.printf("getAveragePriceOfOrdersInTheLastMonth%n%s%n", service.getAveragePriceOfOrdersInTheLastMonth());
        System.out.printf("getTheMostPopularProductInTheStoreFromDate%n%s%n", service.getTheMostPopularProductInTheStoreFromDate(LocalDate.of(2014, 1, 1).atTime(LocalTime.now())));
        System.out.printf("getCountOfOrdersWithProductFromDate%n%s%n", service.getCountOfOrdersWithProductFromDate(productId, LocalDate.of(2014, 1, 1).atTime(LocalTime.now())));
        System.out.println("PRODUCT ID = " + productId);
        System.out.println(LocalDate.of(2014, 1, 1).atTime(LocalTime.now()));
    }

    private static CustomerGenderEntity generateGender() {
        int num = new Random().nextInt(2);
        if (num == 0) {
            return FEMALE;
        }
        return MALE;
    }

    private static OrderStatusEntity generateStatus() {
        int num = new Random().nextInt(3);
        if (num == 0) {
            return PROCESSING;
        } else if (num == 1) {
            return ON_HOLD;
        }
        return COMPLETED;
    }
}
