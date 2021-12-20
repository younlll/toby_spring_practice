package springpractice2.tobyspring1.user.dao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springpractice2.tobyspring1.user.domain.User;
import springpractice2.tobyspring1.user.domain.UserDao;

import java.sql.SQLException;

public class DUserDao {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(DaoFactory.class);
        UserDao dao = context.getBean("userDao", UserDao.class);

        User user = new User();
        user.setId("2001");
        user.setName("DUser01");
        user.setPassword("pwisd01");
        dao.add(user);

        System.out.println("D사 사용자 " + user.getId() + " 등록성공!!!");

        User user2 = dao.get(user.getId());
        System.out.println(user2.getName());
        System.out.println(user2.getPassword());
        System.out.println("D사 사용자 " + user2.getId() + " 조회성공!!!");

    }
}
