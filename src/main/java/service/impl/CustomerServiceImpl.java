package service.impl;

import entity.customer.CustomerEntity;
import repository.CustomerRepository;
import repository.impl.CustomerRepositoryImpl;
import service.CustomerService;

import java.util.List;
import java.util.UUID;

public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository = new CustomerRepositoryImpl();

    @Override
    public CustomerEntity get(UUID id) {
        return customerRepository.get(id).orElseThrow();
    }

    @Override
    public List<CustomerEntity> getAll() {
        return customerRepository.getAll();
    }

    @Override
    public CustomerEntity getWithBiggestCountOfOrders() {
        return customerRepository.getWithBiggestCountOfOrders().orElseThrow();
    }

    @Override
    public CustomerEntity getWithBiggestSumOfOrders() {
        return customerRepository.getWithBiggestSumOfOrders().orElseThrow();
    }

    @Override
    public CustomerEntity getWithBiggestAverageTotalOfOrders() {
        return customerRepository.getWithBiggestAverageTotalOfOrders().orElseThrow();
    }

    @Override
    public CustomerEntity update(CustomerEntity customer) {
        return customerRepository.update(customer).orElseThrow();
    }

    @Override
    public CustomerEntity create(CustomerEntity customer) {
        return customerRepository.create(customer).orElseThrow();
    }

    @Override
    public void deleteById(UUID id) {
        customerRepository.deleteById(id);
    }
}
