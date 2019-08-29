package tests.embedded;

import com.demo.valuetype.embedded.Company;
import com.demo.valuetype.embedded.Contact;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class CompanyContactAttributeOverrideTest {

    private static SessionFactory sessionFactory;

    @BeforeClass
    public static void init() {
        sessionFactory = new Configuration()
                                    .configure("hibernate/hibernate-learning-embedded-tracker-cfg.xml")
                                    .addAnnotatedClass(Company.class)
                                    .buildSessionFactory();
    }

    @Test
    public void test_AttributeOverride_Insert() {
        Transaction transaction = null;
        try(Session session = sessionFactory.getCurrentSession()) {

            Company company = new Company();
            company.setCompanyName("Google");
            company.setOrganization("Alphabet");

            Contact contact = new Contact();
            contact.setHouseNumber("1600");
            contact.setStreet("Amphitheatre Parkway");
            contact.setCity("Mountain View");
            contact.setState("California");
            contact.setCountry("US");
            contact.setZipCode("94043");
            contact.setEmail("google@gmail.com");

            company.setContact(contact);
            transaction = session.beginTransaction();
            session.persist(company);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
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
