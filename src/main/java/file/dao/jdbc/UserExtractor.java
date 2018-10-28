package file.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import file.entity.Role;
import file.entity.User;

public class UserExtractor implements ResultSetExtractor<List<User>> {

    private RowMapper<Role> roleRowMapper;
    
    private RowMapper<User> userRowMapper;
    
    public void setUserRowMapper(RowMapper<User> userRowMapper) {
        this.userRowMapper = userRowMapper;
    }
    
    public void setRoleRowMapper(RowMapper<Role> roleRowMapper) {
        this.roleRowMapper = roleRowMapper;
    }
    
    @Override
    public List<User> extractData(ResultSet rs)
            throws SQLException, DataAccessException {
        
        final List<User> u = new ArrayList<User>();
        int counter = 0;
        User user = null;
        while(rs.next()) {
            if (user == null || !Long.valueOf(rs.getLong("user_id")).equals(user.getId())) {
                user = userRowMapper.mapRow(rs, counter);
                user.setRole(new Role());
                u.add(user);
            }
            counter++;
        }
        return u;
    }

}
