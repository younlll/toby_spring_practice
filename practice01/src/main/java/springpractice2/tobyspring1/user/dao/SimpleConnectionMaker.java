package springpractice2.tobyspring1.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DB연결이라는 역할을 하는 SimpleConnectionMaker 클래스를 만들어 분리시킴
 */
public class SimpleConnectionMaker {
    public Connection makeNewConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/spring_study", "root", "root"
        );
        return conn;
    }
}
