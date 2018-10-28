package file.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import file.entity.Role;

public class RoleRowMapper implements RowMapper<Role> {
    
    @Override
    public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
        
        final Role r = new Role();
        r.setId(rs.getLong("id"));
        r.setName(rs.getString("name"));
        return r;
    }
    
}

