package springpractice2.tobyspring1.user.dao;

import com.sun.nio.sctp.SctpSocketOption;
import org.springframework.dao.EmptyResultDataAccessException;
import springpractice2.tobyspring1.user.domain.User;

import javax.sql.DataSource;
import javax.swing.plaf.nimbus.State;
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
        StatementStrategy strategy = new AddStatement(user);
        jdbcContextWithStatementStrategy(strategy);
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = dataSource.getConnection();
            ps = conn.prepareStatement("select * from users where id = ?");
            ps.setString(1, id);
            rs = ps.executeQuery();

            this.user = null;
            if(rs.next()) {
                this.user = new User();
                this.user.setId(rs.getString("id"));
                this.user.setName(rs.getString("name"));
                this.user.setPassword(rs.getString("password"));
            }

            if(this.user == null) {
                throw new EmptyResultDataAccessException(1);
            }
            return this.user;
        } catch (SQLException e) {
            throw e;
        } finally {
            if(rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {}
            }

            if(ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {}
            }

            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {}
            }
        }
    }

    public void deleteAll() throws SQLException {
        StatementStrategy strategy = new DeleteAllStatement();
        jdbcContextWithStatementStrategy(strategy);
    }

    public int getCount() throws SQLException {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = dataSource.getConnection();
            ps = conn.prepareStatement("select count(*) from users");

            rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            throw e;
        } finally {
            if(rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {}
            }

            if(ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {}
            }

            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {}
            }
        }
    }

    public void jdbcContextWithStatementStrategy(StatementStrategy stmt) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = dataSource.getConnection();
            ps = stmt.makePePreparedStatement(conn);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw  e;
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {}
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {}
            }
        }
    }
}