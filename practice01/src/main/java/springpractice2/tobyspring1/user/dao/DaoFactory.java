package springpractice2.tobyspring1.user.dao;

import spring.user.domain.UserDao;

//test
public class DaoFactory {
    public UserDao userDao() {
        return new UserDao(connectionMaker());
    }

    public ConnectionMaker connectionMaker() {
        return new DConnectionMaker();
    }
}
