package mapping.valuetype;

import com.entity.listmapping.Professor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class ListMappingTest {

    private static SessionFactory sessionFactory;

    @BeforeClass
    public static void init() {
        System.out.println("Initialized ");
        sessionFactory = new Configuration().configure("hibernate/hibernate-professor-tracker-cfg.xml")
                .addAnnotatedClass(Professor.class)
                .buildSessionFactory();

    }

    @Test
    public void test_Insert() {

        Transaction transaction = null;
        try(Session session = sessionFactory.getCurrentSession();) {
            Professor professor = new Professor("Ross", "Geller", "dr_geller@friend.com");
            List<String> images = professor.getImages();

            images.add("photo1.jpg");
            images.add("photo2.jpg");
            images.add("photo3.jpg");
            images.add("photo4.jpg");
            images.add("photo5.jpg");

            transaction = session.beginTransaction();
            session.persist(professor);
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
