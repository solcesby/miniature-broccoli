package utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerUtils {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-persistence-unit");

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

}
