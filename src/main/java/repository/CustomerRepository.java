package repository;

import entity.customer.CustomerEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository {

    Optional<CustomerEntity> get(UUID id);

    List<CustomerEntity> getAll();

    Optional<CustomerEntity> getWithBiggestCountOfOrders();

    Optional<CustomerEntity> getWithBiggestSumOfOrders();

    Optional<CustomerEntity> getWithBiggestAverageTotalOfOrders();

    Optional<CustomerEntity> update(CustomerEntity customer);

    Optional<CustomerEntity> create(CustomerEntity customer);

    void deleteById(UUID id);

}
