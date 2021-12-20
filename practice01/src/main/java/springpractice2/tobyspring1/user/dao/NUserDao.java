package springpractice2.tobyspring1.user.dao;

import spring.user.domain.User;
import spring.user.domain.UserDao;

import java.sql.SQLException;

public class NUserDao {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        UserDao dao = new DaoFactory().userDao();

        User user = new User();
        user.setId("1001");
        user.setName("NUser01");
        user.setPassword("pwisn01");

        dao.add(user);

        System.out.println("N사 사용자 " + user.getId() + " 등록성공!!!");

        User user2 = dao.get(user.getId());
        System.out.println(user2.getName());
        System.out.println(user2.getPassword());
        System.out.print("N사 사용자 " + user.getId() + " 조회성공!!!");
    }
}
