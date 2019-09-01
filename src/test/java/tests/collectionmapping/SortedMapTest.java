package tests.collectionmapping;

import com.demo.valuetype.elementcollection.sorted.mapmapping.Tourist;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.*;

import java.util.AbstractMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SortedMapTest {

    private static SessionFactory sessionFactory;

    @BeforeClass
    public static void init() {
        sessionFactory = new Configuration().configure("hibernate/hibernate-learning-elementcollection-tracker-cfg.xml")
                                            .addAnnotatedClass(Tourist.class)
                                            .buildSessionFactory();
    }

    @Test
    public void test_Insert() {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()){
            Tourist tourist =
                    new Tourist("Ross", "Geller");

            Map<String, String> places = Stream.of(
                    new AbstractMap.SimpleEntry<>("AU", "Australia"),
                    new AbstractMap.SimpleEntry<>("AT", "Austria"),
                    new AbstractMap.SimpleEntry<>("BT", "Bhutan")
            ).collect(
                    Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue,
                            (k,v) -> v, LinkedHashMap::new));
            tourist.setPlaces(places);
            transaction = session.beginTransaction();
            session.persist(tourist);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test_Select_Returns_In_Order_By() {
        try (Session session = sessionFactory.getCurrentSession()){
            session.beginTransaction();
            Tourist tourist = session.get(Tourist.class, 102);
            System.out.println(tourist.getPlaces().getClass().getName());
            tourist.getPlaces()
                    .forEach((s1, s2) -> System.out.println(String.format("%s-%s", s1, s2)));
            session.getTransaction().commit();
        } catch (Exception e) {
            sessionFactory.getCurrentSession()
                    .getTransaction().rollback();
            Assert.fail(e.getMessage());
        }
    }

    @AfterClass
    public static void destory() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
