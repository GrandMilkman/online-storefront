package file.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import file.entity.User;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {

        final User u = new User();
        u.setId(rs.getLong("id"));
        u.setName(rs.getString("name"));
        u.setPassword(rs.getString("password"));
        return u;
    }

}
