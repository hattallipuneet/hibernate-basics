package tests.embedded;

import com.demo.valuetype.embedded.Contact;
import com.demo.valuetype.embedded.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserContactEmbeddedTest {

    private static SessionFactory sessionFactory;

    @BeforeClass
    public static void init() {
        sessionFactory = new Configuration()
                .configure("hibernate/hibernate-learning-embedded-tracker-cfg.xml")
                .addAnnotatedClass(User.class)
                .buildSessionFactory();
    }

    @Test
    public  void test_EmbeddedInsert() {
        Transaction transaction = null;
        try(Session session = sessionFactory.getCurrentSession()) {

            User user = new User();
            user.setFirstName("Rachel");
            user.setLastName("Greene");

            Contact contact = new Contact();
            contact.setEmail("evergreenrachel@friends.com");
            contact.setHouseNumber("111");
            contact.setStreet("Main Avenue");
            contact.setCity("New York");
            contact.setState("New York");
            contact.setCountry("US");
            contact.setZipCode("10453");

            user.setContact(contact);

            transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }

    }

    @AfterClass
    public static void destory() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
