package mapping.valuetype;


import com.entity.setmapping.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Set;

public class SetMappingTest {

    private static SessionFactory sessionFactory;

    @BeforeClass
    public static void init() {
        System.out.println("Initialized ");
        sessionFactory = new Configuration().configure("hibernate/hibernate-student-tracker-cfg.xml")
                                            .addAnnotatedClass(Student.class)
                                            .buildSessionFactory();

    }

    @Test
    public void test_Insert() {

        Transaction transaction = null;
        try(Session session = sessionFactory.getCurrentSession();) {
            Student student = new Student("Ross", "Geller", "dr_geller@friend.com");
            Set<String> images = student.getImages();

            images.add("photo1.jpg");
            images.add("photo2.jpg");
            images.add("photo3.jpg");
            images.add("photo4.jpg");
            images.add("photo5.jpg");

            transaction = session.beginTransaction();
            session.persist(student);
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @AfterClass
    public static void destroy() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

}
