package file.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import file.entity.Role;

@Component
public class RoleRowMapper implements RowMapper<Role> {

    @Override
    public Role mapRow(ResultSet rs, int rowNum) throws SQLException {

        final Role r = new Role();
        r.setId(rs.getLong("role_id"));
        r.setName(rs.getString("role_name"));
        return r;
    }

}
