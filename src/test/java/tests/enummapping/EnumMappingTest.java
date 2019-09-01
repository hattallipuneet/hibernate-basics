package tests.enummapping;

import com.demo.enummapping.Process;
import com.demo.enummapping.ProcessStatus;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class EnumMappingTest {

    private static SessionFactory sessionFactory;

    @BeforeClass
    public static void init() {
        sessionFactory = new Configuration()
                            .configure("hibernate/hibernate-learning-enum-mapping-tracker-cfg.xml")
                            .addAnnotatedClass(Process.class)
                            .buildSessionFactory();
    }

    @Test
    public void test_Enum_Mapping_Test() {
        Transaction transaction = null;

        try(Session session = sessionFactory.getCurrentSession();) {

            Process chrome =
                    new Process("Chrome",
                            "Chrome Browser",
                            ProcessStatus.ACTIVE);

            Process intelliJ =
                    new Process("IntelliJ",
                            "JetBrains Java IDE",
                            ProcessStatus.STOP);

            transaction = session.beginTransaction();
            session.persist(chrome);
            session.persist(intelliJ);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @AfterClass
    public static void destroy() {
        if (sessionFactory!= null) {
            sessionFactory.close();
        }
    }
}
