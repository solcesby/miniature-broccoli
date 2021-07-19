import com.github.javafaker.Faker;
import entity.customer.CustomerEntity;
import entity.customer.enums.CustomerGenderEntity;
import entity.order.OrderEntity;
import entity.order.enums.OrderStatusEntity;
import entity.orderdetails.OrderDetailsEntity;
import entity.product.ProductEntity;
import entity.productcategory.ProductCategoryEntity;
import service.*;
import service.impl.*;

import java.util.Date;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import static entity.customer.enums.CustomerGenderEntity.FEMALE;
import static entity.customer.enums.CustomerGenderEntity.MALE;
import static entity.order.enums.OrderStatusEntity.*;

public class Main {
    public static void main(String[] args) {
        Faker faker = new Faker();
        ProductCategoryService productCategoryService = new ProductCategoryServiceImpl();
        CustomerService customerService = new CustomerServiceImpl();
        ProductService productService = new ProductServiceImpl();
        OrderService orderService = new OrderServiceImpl();
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
                    .birthDate(faker.date().birthday())
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
                    .orderDate(faker.date().between(customer.getBirthDate(), new Date()))
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
        System.out.printf("getTheMostPopularProductInTheStoreFromDate%n%s", service.getTheMostPopularProductInTheStoreFromDate(new Date(2014, 6, 12)));
        System.out.printf("getCountOfOrdersWithProductFromDate%n%s", service.getCountOfOrdersWithProductFromDate(productId, new Date(2014, 6, 12)));
        System.out.printf("getAveragePriceOfOrdersInTheLastMonth%n%s", service.getAveragePriceOfOrdersInTheLastMonth());
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
