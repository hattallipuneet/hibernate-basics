package tests.inheritance;

import com.demo.inheritance.singletable.Account;
import com.demo.inheritance.singletable.CreditAccount;
import com.demo.inheritance.singletable.DebitAccount;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SingleTableInheritanceTest {

    private static SessionFactory sessionFactory;

    @BeforeClass
    public static void init() {
        sessionFactory = new Configuration()
                                .configure("hibernate/hibernate-learning-single-table-inherit-cfg.xml")
                                .addAnnotatedClass(DebitAccount.class)
                                .addAnnotatedClass(CreditAccount.class)
                                .buildSessionFactory();

//        insertRecords();

    }

    private static void insertRecords() {
        Transaction transaction = null;
        try(Session session = sessionFactory.getCurrentSession();) {
            DebitAccount debitAccount = new DebitAccount();
            debitAccount.setOwner( "John Doe" );
            debitAccount.setBalance( BigDecimal.valueOf( 100 ) );
            debitAccount.setInterestRate( BigDecimal.valueOf( 1.5d ) );
            debitAccount.setOverDraftFee( BigDecimal.valueOf( 25 ) );

            CreditAccount creditAccount = new CreditAccount();
            creditAccount.setOwner( "John Doe" );
            creditAccount.setBalance( BigDecimal.valueOf( 1000 ) );
            creditAccount.setInterestRate( BigDecimal.valueOf( 1.9d ) );
            creditAccount.setCreditLimit( BigDecimal.valueOf( 5000 ) );

            transaction = session.beginTransaction();

            session.persist(debitAccount);
            session.persist(creditAccount);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            Assert.fail(e.getMessage());
        }
    }

//    @Test
    public void test_insert_records() {
        Transaction transaction = null;
        try(Session session = sessionFactory.getCurrentSession();) {
            DebitAccount debitAccount = new DebitAccount();
            debitAccount.setOwner( "John Doe" );
            debitAccount.setBalance( BigDecimal.valueOf( 2000 ) );
            debitAccount.setInterestRate( BigDecimal.valueOf( 1.7d ) );
            debitAccount.setOverDraftFee( BigDecimal.valueOf( 45 ) );

            CreditAccount creditAccount = new CreditAccount();
            creditAccount.setOwner( "John Doe" );
            creditAccount.setBalance( BigDecimal.valueOf( 3000 ) );
            creditAccount.setInterestRate( BigDecimal.valueOf( 2.9d ) );
            creditAccount.setCreditLimit( BigDecimal.valueOf( 1200 ) );

            transaction = session.beginTransaction();

            session.persist(debitAccount);
            session.persist(creditAccount);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test_Select_Query() {
        try(Session session = sessionFactory.openSession()) {
            NativeQuery<Account> nativeQuery = session.createNativeQuery("select * from account", Account.class);
            List<Account> accounts = nativeQuery.list();

            Map<String, List<Account>> accountTypeMap = accounts.stream()
                                                        .collect(Collectors.groupingBy(
                                                                account -> account.getClass().getSimpleName())
                                                        );
            accountTypeMap.forEach((accountType, accList) -> {
                System.out.println(String.format("There are %d %s accounts", accList.size(), accountType));
            });

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
