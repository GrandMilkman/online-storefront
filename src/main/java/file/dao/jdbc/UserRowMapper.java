package file.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import file.entity.User;

@Component
public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {

        final User u = new User();
        u.setId(rs.getLong("user_id"));
        u.setName(rs.getString("user_name"));
        u.setPassword(rs.getString("user_password"));
        return u;
    }

}
