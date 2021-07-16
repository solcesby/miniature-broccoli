package repository.impl;

import entity.orderdetails.OrderDetailsEntity;
import repository.OrderDetailsRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static utils.EntityManagerUtils.getEntityManager;

public class OrderDetailsRepositoryImpl implements OrderDetailsRepository {

    @PersistenceContext
    private EntityManager em = getEntityManager();

    @Override
    public Optional<OrderDetailsEntity> get(final UUID id) {
        return Optional.ofNullable(em.find(OrderDetailsEntity.class, id));
    }

    @Override
    public List<OrderDetailsEntity> getAll() {
        return em.createQuery(
                "SELECT od " +
                        "FROM OrderDetailsEntity od", OrderDetailsEntity.class)
                .getResultList();
    }

    @Override
    public List<OrderDetailsEntity> getByOrderId(final UUID id) {
        return em.createQuery(
                "SELECT od " +
                        "FROM OrderDetailsEntity od " +
                        "WHERE od.id = :id", OrderDetailsEntity.class)
                .setParameter("id", id)
                .getResultList();
    }

    @Override
    public Optional<Long> getCountOfOrdersWithProductFromDate(UUID productId, Date date) {
        return Optional.of((Long) em.createQuery(
                "SELECT count(od) " +
                        "FROM OrderDetailsEntity od " +
                        "WHERE od.id = :id AND od.order.orderDate >= :date")
                .setParameter("id", productId)
                .setParameter("date", date)
                .getSingleResult());
    }

    @Override
    public Optional<OrderDetailsEntity> update(final OrderDetailsEntity orderDetails) {
        em.getTransaction().begin();
        final OrderDetailsEntity updatedOrderDetails = em.merge(orderDetails);
        em.getTransaction().commit();
        return Optional.of(updatedOrderDetails);
    }

    @Override
    public Optional<OrderDetailsEntity> create(final OrderDetailsEntity orderDetails) {
        em.getTransaction().begin();
        em.persist(orderDetails);
        em.getTransaction().commit();
        return Optional.of(em.find(OrderDetailsEntity.class, orderDetails.getId()));
    }

    @Override
    public void deleteById(final UUID id) {
        final OrderDetailsEntity orderDetailsToDelete = em.find(OrderDetailsEntity.class, id);
        em.getTransaction().begin();
        em.remove(orderDetailsToDelete);
        em.getTransaction().commit();
    }
}
