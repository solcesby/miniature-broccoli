package repository.impl;

import entity.order.OrderEntity;
import repository.OrderRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.Calendar.DATE;
import static java.util.Calendar.MONTH;
import static utils.EntityManagerUtils.getEntityManager;

public class OrderRepositoryImpl implements OrderRepository {

    @PersistenceContext
    private EntityManager em = getEntityManager();

    @Override
    public Optional<OrderEntity> get(final UUID id) {
        return Optional.ofNullable(em.find(OrderEntity.class, id));
    }

    @Override
    public List<OrderEntity> getAll() {
        return em.createQuery(
                "SELECT o " +
                        "FROM OrderEntity o", OrderEntity.class)
                .getResultList();
    }

    @Override
    public List<OrderEntity> getByCustomerId(UUID id) {
        return em.createQuery(
                "SELECT o " +
                        "FROM OrderEntity o " +
                        "WHERE o.customer.id = :id", OrderEntity.class)
                .setParameter("id", id)
                .getResultList();
    }

    @Override
    public List<OrderEntity> getAllOrdersInTheLastTwoWeeks() {
        final Calendar calendar = Calendar.getInstance();
        calendar.add(DATE, -14);
        return em.createQuery(
                "SELECT o " +
                        "FROM OrderEntity o " +
                        "WHERE o.orderDate >= :date", OrderEntity.class)
                .setParameter("date",
                        calendar.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())
                .getResultList();
    }

    @Override
    public Optional<Double> getAveragePriceOfOrdersInTheLastMonth() {
        final Calendar calendar = Calendar.getInstance();
        calendar.add(MONTH, -1);
        return Optional.of((Double) em.createQuery(
                "SELECT avg(o.totalPrice) " +
                        "FROM OrderEntity o " +
                        "WHERE o.orderDate >= :date")
                .setParameter("date",
                        calendar.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())
                .getSingleResult());
    }

    @Override
    public Optional<OrderEntity> update(final OrderEntity order) {
        em.getTransaction().begin();
        final OrderEntity updatedOrder = em.merge(order);
        em.getTransaction().commit();
        return Optional.of(updatedOrder);
    }

    @Override
    public Optional<OrderEntity> create(final OrderEntity order) {
        em.getTransaction().begin();
        em.persist(order);
        em.getTransaction().commit();
        return Optional.of(em.find(OrderEntity.class, order.getId()));
    }

    @Override
    public void deleteById(final UUID id) {
        final OrderEntity orderToDelete = em.find(OrderEntity.class, id);
        em.getTransaction().begin();
        em.remove(orderToDelete);
        em.getTransaction().commit();
    }
}
