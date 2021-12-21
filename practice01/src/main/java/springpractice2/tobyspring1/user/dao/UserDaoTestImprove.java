package springpractice2.tobyspring1.user.dao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import springpractice2.tobyspring1.user.domain.User;

import java.sql.SQLException;

public class UserDaoTestImprove {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");

        UserDao dao = context.getBean("userDao", UserDao.class);

        User user = new User();
        user.setId("3001");
        user.setName("youn");
        user.setPassword("pwisyoun");

        dao.add(user);

        System.out.println(user.getId() + " 등록성공!!!");

        User user2 = dao.get(user.getId());
        if (!user.getName().equals(user2.getName())) {
            System.out.println("테스트 실패 (name)");
        } else if (!user.getPassword().equals(user2.getPassword())) {
            System.out.println("테스트 실패 (password)");
        } else {
            System.out.println("조회 테스트 성공!!!");
        }
    }
}
