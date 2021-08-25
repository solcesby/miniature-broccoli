package repository.impl;

import entity.productcategory.ProductCategoryEntity;
import repository.ProductCategoryRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static utils.EntityManagerUtils.getEntityManager;

public class ProductCategoryRepositoryImpl implements ProductCategoryRepository {

    @PersistenceContext
    private EntityManager em = getEntityManager();

    @Override
    public Optional<ProductCategoryEntity> get(final UUID id) {
        return Optional.ofNullable(em.find(ProductCategoryEntity.class, id));
    }

    @Override
    public List<ProductCategoryEntity> getAll() {
        return em.createQuery(
                "SELECT pc " +
                        "FROM ProductCategoryEntity pc", ProductCategoryEntity.class)
                .getResultList();
    }

    @Override
    public Optional<ProductCategoryEntity> update(final ProductCategoryEntity productCategory) {
        em.getTransaction().begin();
        final ProductCategoryEntity updatedProductCategory = em.merge(productCategory);
        em.getTransaction().commit();
        return Optional.of(updatedProductCategory);
    }

    @Override
    public Optional<ProductCategoryEntity> create(final ProductCategoryEntity productCategory) {
        em.getTransaction().begin();
        em.persist(productCategory);
        em.getTransaction().commit();
        return Optional.of(em.find(ProductCategoryEntity.class, productCategory.getId()));
    }

    @Override
    public void deleteById(UUID id) {
        final ProductCategoryEntity productCategoryToDelete = em.find(ProductCategoryEntity.class, id);
        em.getTransaction().begin();
        em.remove(productCategoryToDelete);
        em.getTransaction().commit();
    }
}
