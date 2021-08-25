package repository.impl;

import entity.product.ProductEntity;
import repository.ProductRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static utils.EntityManagerUtils.getEntityManager;

public class ProductRepositoryImpl implements ProductRepository {

    @PersistenceContext
    private EntityManager em = getEntityManager();

    @Override
    public Optional<ProductEntity> get(final UUID id) {
        return Optional.ofNullable(em.find(ProductEntity.class, id));
    }

    @Override
    public List<ProductEntity> getAll() {
        return em.createQuery(
                "SELECT p " +
                        "FROM ProductEntity p", ProductEntity.class)
                .getResultList();
    }

    @Override
    public Optional<ProductEntity> getTheMostPopularProductFromDate(LocalDateTime date) {
        return Optional.of(em.createQuery(
                "SELECT p " +
                        "FROM ProductEntity p " +
                        "INNER JOIN p.orderDetails od " +
                        "GROUP BY p.id, od.order.id, od.order.orderDate " +
                        "HAVING od.order.orderDate >= :date " +
                        "ORDER BY count(p) DESC", ProductEntity.class)
                .setParameter("date", date)
                .getResultList().get(0));
    }

    @Override
    public Optional<ProductEntity> update(final ProductEntity product) {
        em.getTransaction().begin();
        final ProductEntity updatedProduct = em.merge(product);
        em.getTransaction().commit();
        return Optional.of(updatedProduct);
    }

    @Override
    public Optional<ProductEntity> create(final ProductEntity product) {
        em.getTransaction().begin();
        em.persist(product);
        em.getTransaction().commit();
        return Optional.of(em.find(ProductEntity.class, product.getId()));
    }

    @Override
    public void deleteById(final UUID id) {
        final ProductEntity productToDelete = em.find(ProductEntity.class, id);
        em.getTransaction().begin();
        em.remove(productToDelete);
        em.getTransaction().commit();
    }
}
