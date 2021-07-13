import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManager em = Persistence.createEntityManagerFactory("hibernate-persistence-unit").createEntityManager();
//        em.getTransaction().begin();
//        em.persist(CustomerEntity.builder()
//                .firstName("Bob")
//                .lastName("Bobson")
//                .email("bob@gmail.com")
//                .address("Bobville")
//                .gender(MALE)
//                .birthDate(new Date(2000, 6, 8))
//                .build());
    }
}
