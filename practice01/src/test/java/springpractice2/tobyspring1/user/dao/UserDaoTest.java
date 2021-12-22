package springpractice2.tobyspring1.user.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import springpractice2.tobyspring1.user.domain.User;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserDaoTest {
    private UserDao dao;
    private User user1;
    private User user2;
    private User user3;

    @BeforeEach // @Test 메소드가 실행되기 전에 먼저 실행되어야 하는 메소드를 정의
    public void setUp() {
        ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
        this.dao = context.getBean("userDao", UserDao.class);

        this.user1 = new User("1000", "youn", "pwisyoun");
        this.user2 = new User("1001", "intellij", "pwisj");
        this.user3 = new User("1002", "hotsix", "pwis6");
    }

    @Test   // JUnit에 테스트용 메소드임을 알린다
    public void addAndGet() throws SQLException, ClassNotFoundException {
//        User user1 = new User("1000", "youn", "pwisyoun");
//        User user2 = new User("1001", "intellij", "pwisj");

        dao.deleteAll();
        assertEquals(dao.getCount(), 0);

        dao.add(user1);
        dao.add(user2);

        assertEquals(dao.getCount(), 2);

        User userget1 = dao.get(user1.getId());
        assertEquals(userget1.getName(), user1.getName());
        assertEquals(userget1.getPassword(), user1.getPassword());

        User userget2 = dao.get(user2.getId());
        assertEquals(userget2.getName(), user2.getName());
        assertEquals(userget2.getPassword(), user2.getPassword());
    }

    @Test
    public void count() throws ClassNotFoundException, SQLException {
//        User user1 = new User("1000", "youn", "pwisyoun");
//        User user2 = new User("1001", "intellij", "pwisj");
//        User user3 = new User("1002", "hotsix", "pwis6");

        dao.deleteAll();
        assertEquals(dao.getCount(), 0);

        dao.add(user1);
        assertEquals(dao.getCount(), 1);

        dao.add(user2);
        assertEquals(dao.getCount(), 2);

        dao.add(user3);
        assertEquals(dao.getCount(), 3);
    }

    @Test
    public void getUserFailure() throws ClassNotFoundException, SQLException {
        dao.deleteAll();
        assertEquals(dao.getCount(), 0);

        assertThrows(EmptyResultDataAccessException.class, () -> {
            dao.get("unknown_id");
        });
    }
}
