import entity.customer.CustomerEntity;
import service.CustomerService;
import service.OrderService;
import service.StatisticService;
import service.impl.CustomerServiceImpl;
import service.impl.OrderServiceImpl;
import service.impl.StatisticServiceImpl;
import utils.EntityManagerUtils;

import javax.persistence.EntityManager;
import java.util.Calendar;
import java.util.UUID;

public class Test {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -2);
        StatisticService statisticService = new StatisticServiceImpl();
        CustomerService customerService = new CustomerServiceImpl();
        EntityManager em = EntityManagerUtils.getEntityManager();
        OrderService orderService = new OrderServiceImpl();
        System.out.println("==============================================================================");
        UUID id = customerService.getAll().get(0).getId();
        System.out.println("==============================================================================");
    }
}
