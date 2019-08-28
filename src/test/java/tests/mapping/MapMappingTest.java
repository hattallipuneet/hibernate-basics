package tests.mapping;

import com.entity.mapmapping.StockHolder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MapMappingTest {

    private static SessionFactory sessionFactory;

    @BeforeClass
    public static void init() {
        sessionFactory = new Configuration().configure("hibernate/hibernate-learning-tracker-cfg.xml")
                                            .addAnnotatedClass(StockHolder.class)
                                            .buildSessionFactory();
    }

    @Test
    public void test_Insert() {
        Transaction transaction = null;
        try(Session session = sessionFactory.getCurrentSession();) {

            Map<String, String> assets = Stream.of(
                    new AbstractMap.SimpleEntry<>("GOOG", "Google"),
                    new AbstractMap.SimpleEntry<>("APPL", "Apple"),
                    new AbstractMap.SimpleEntry<>("MSFT", "Micosoft")
            ).collect(Collectors.toMap(Map.Entry::getKey,
                                        Map.Entry::getValue));

            StockHolder stockHolder = new StockHolder("Monica", "Geller");
            stockHolder.setAssets(assets);
            transaction = session.beginTransaction();
            session.persist(stockHolder);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
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
