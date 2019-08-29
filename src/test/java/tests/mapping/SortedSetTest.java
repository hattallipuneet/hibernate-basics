package tests.mapping;

import com.demo.valuetype.elementcollection.sorted.setmapping.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SortedSetTest {

    private static SessionFactory sessionFactory;

    @BeforeClass
    public static void init() {
        sessionFactory = new Configuration().configure("hibernate/hibernate-learning-elementcollection-tracker-cfg.xml")
                                            .addAnnotatedClass(Employee.class)
                                            .buildSessionFactory();
    }

    @Test
    public void test_Insert() {
        Transaction transaction = null;

        try(Session session = sessionFactory.getCurrentSession()) {

            Employee employee =
                    new Employee("Chandler", "Bing", "cmbing@friends.com");
            Set<Integer> salaries = IntStream.of(10000,15000,20000,18000)
                                            .boxed()
                                            .collect(Collectors.toCollection(LinkedHashSet::new));
            employee.setSalaries(salaries);
            transaction = session.beginTransaction();
            session.persist(employee);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test_Select_Return_Sorted_Data() {
        try(Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Employee rachel = session.get(Employee.class, 102);
            System.out.println(rachel.getSalaries());
            session.getTransaction().commit();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @AfterClass
    public static void destroy() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
