package springpractice2.tobyspring1.user.domain;

import springpractice2.tobyspring1.user.dao.ConnectionMaker;
import springpractice2.tobyspring1.user.dao.DaoFactory;

import java.sql.*;

public class UserDao {
    private ConnectionMaker connectionMaker;
    private Connection conn;
    private User user;

    public UserDao() {
        DaoFactory daoFactory = new DaoFactory();
        this.connectionMaker = daoFactory.connectionMaker();
    }

    public void add(User user) throws ClassNotFoundException, SQLException {
        this.conn = connectionMaker.makeConnection();

        PreparedStatement ps = conn.prepareStatement(
                "insert into users(id, name, password) values (?, ?, ?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();

        ps.close();
        conn.close();
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        this.conn = connectionMaker.makeConnection();

        PreparedStatement ps = conn.prepareStatement(
                "select * from users where id = ?");
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();
        rs.next();
        this.user = new User();
        this.user.setId(rs.getString("id"));
        this.user.setName(rs.getString("name"));
        this.user.setPassword(rs.getString("password"));

        rs.close();
        ps.close();
        this.conn.close();

        return this.user;
    }
}
