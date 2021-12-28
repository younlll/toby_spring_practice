package springpractice2.tobyspring1.user.dao;

import com.sun.nio.sctp.SctpSocketOption;
import org.springframework.dao.EmptyResultDataAccessException;
import springpractice2.tobyspring1.user.domain.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private DataSource dataSource;
    private User user;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void add(User user) throws ClassNotFoundException, SQLException {
        Connection conn = dataSource.getConnection();

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
        Connection conn = dataSource.getConnection();

        PreparedStatement ps = conn.prepareStatement(
                "select * from users where id = ?");
        ps.setString(1, id);

        this.user = null;
        ResultSet rs = ps.executeQuery();
        if(rs.next()) {
            this.user = new User();
            this.user.setId(rs.getString("id"));
            this.user.setName(rs.getString("name"));
            this.user.setPassword(rs.getString("password"));
        }

        rs.close();
        ps.close();
        conn.close();

        if (user == null) {
            throw new EmptyResultDataAccessException(1);
        }

        return this.user;
    }

    public void deleteAll() throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = dataSource.getConnection();
            ps = conn.prepareStatement("delete from users");
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {

                }
            }
            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {

                }
            }
        }
    }

    public int getCount() throws SQLException {
        Connection conn = dataSource.getConnection();

        PreparedStatement ps = conn.prepareStatement("select count(*) from users");

        ResultSet rs = ps.executeQuery();
        rs.next();
        int count = rs.getInt(1);

        rs.close();
        ps.close();
        conn.close();

        return count;
    }
}