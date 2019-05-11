package file.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import file.entity.User;

public class UserRowMapper implements RowMapper<User> {

    public User mapRow(final ResultSet rs, final int count) throws SQLException {

        final User u = new User();
        u.setId(rs.getLong("user_id"));
        u.setName(rs.getString("user_name"));
        u.setPassword(rs.getString("user_password"));
        u.setMail(rs.getString("user_mail"));
        u.setConfirm(rs.getString("user_confirm"));
        return u;
    }

}
