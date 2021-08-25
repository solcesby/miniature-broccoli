package repository.impl;

import entity.customer.CustomerEntity;
import repository.CustomerRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static utils.EntityManagerUtils.getEntityManager;

public class CustomerRepositoryImpl implements CustomerRepository {

    @PersistenceContext
    private EntityManager em = getEntityManager();

    @Override
    public Optional<CustomerEntity> get(final UUID id) {
        return Optional.ofNullable(em.find(CustomerEntity.class, id));
    }

    @Override
    public List<CustomerEntity> getAll() {
        return em.createQuery(
                "SELECT c " +
                        "FROM CustomerEntity c", CustomerEntity.class)
                .getResultList();
    }

    @Override
    public Optional<CustomerEntity> getWithBiggestCountOfOrders() {
        return Optional.of(em.createQuery(
                "SELECT c " +
                        "FROM CustomerEntity c " +
                        "GROUP BY c.id " +
                        "ORDER BY size(c.orders) DESC", CustomerEntity.class)
                .getResultList().get(0));
    }

    @Override
    public Optional<CustomerEntity> getWithBiggestSumOfOrders() {
        return Optional.of(em.createQuery(
                "SELECT c " +
                        "FROM CustomerEntity c " +
                        "INNER JOIN c.orders AS o " +
                        "GROUP BY c.id " +
                        "ORDER BY sum(o.totalPrice) DESC ", CustomerEntity.class)
                .getResultList().get(0));
    }

    @Override
    public Optional<CustomerEntity> getWithBiggestAverageTotalOfOrders() {
        return Optional.of(em.createQuery(
                "SELECT c " +
                        "FROM CustomerEntity c " +
                        "INNER JOIN c.orders o " +
                        "GROUP BY c.id " +
                        "ORDER BY avg(o.totalPrice) DESC", CustomerEntity.class)
                .getResultList().get(0));
    }

    @Override
    public Optional<CustomerEntity> update(final CustomerEntity customer) {
        em.getTransaction().begin();
        final CustomerEntity updatedCustomer = em.merge(customer);
        em.getTransaction().commit();
        return Optional.of(updatedCustomer);
    }

    @Override
    public Optional<CustomerEntity> create(final CustomerEntity customer) {
        em.getTransaction().begin();
        em.persist(customer);
        em.getTransaction().commit();
        return Optional.of(em.find(CustomerEntity.class, customer.getId()));
    }

    @Override
    public void deleteById(final UUID id) {
        final CustomerEntity customerToDelete = em.find(CustomerEntity.class, id);
        em.getTransaction().begin();
        em.remove(customerToDelete);
        em.getTransaction().commit();
    }
}
