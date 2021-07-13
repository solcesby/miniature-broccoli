package utils;

import entity.customer.CustomerEntity;
import entity.order.OrderEntity;
import entity.orderdetails.OrderDetailsEntity;
import entity.product.ProductEntity;
import entity.productcategory.ProductCategoryEntity;
import lombok.SneakyThrows;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

import static org.hibernate.cfg.AvailableSettings.*;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                Properties properties = new Properties();
                properties.put(DRIVER, "org.postgresql.Driver");
                properties.put(URL, "jdbc:mysql://localhost:3306/online_store");
                properties.put(USER, "postgres");
                properties.put(PASS, "root");
                properties.put(DIALECT, "org.hibernate.dialect.PostgreSQL82Dialect");
                properties.put(SHOW_SQL, "true");
                properties.put(CURRENT_SESSION_CONTEXT_CLASS, "thread");
                properties.put(HBM2DDL_AUTO, "create-drop");

                configuration.setProperties(properties);

                configuration.addAnnotatedClass(CustomerEntity.class);
                configuration.addAnnotatedClass(OrderEntity.class);
                configuration.addAnnotatedClass(OrderDetailsEntity.class);
                configuration.addAnnotatedClass(ProductEntity.class);
                configuration.addAnnotatedClass(ProductCategoryEntity.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

}
