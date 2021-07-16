package service;

import entity.customer.CustomerEntity;

import java.util.List;
import java.util.UUID;

public interface CustomerService {

    CustomerEntity get(UUID id);

    List<CustomerEntity> getAll();

    CustomerEntity getWithBiggestCountOfOrders();

    CustomerEntity getWithBiggestSumOfOrders();

    CustomerEntity getWithBiggestAverageTotalOfOrders();

    CustomerEntity update(CustomerEntity customer);

    CustomerEntity create(CustomerEntity customer);

    void deleteById(UUID id);

}
