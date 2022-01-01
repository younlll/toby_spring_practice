package springpractice2.tobyspring1.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface StatementStrategy {
    PreparedStatement makePePreparedStatement(Connection conn) throws SQLException;
}
