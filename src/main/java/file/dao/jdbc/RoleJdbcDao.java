package file.dao.jdbc;

import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import file.dao.RoleDao;
import file.entity.Role;

public class RoleJdbcDao extends JdbcDaoSupport implements RoleDao{
    
    
    private RoleRowMapper roleRowMapper;

    @Override
    public List<Role> getAll() {
        
        return getJdbcTemplate().query("SELECT r.role_id AS role_id, r.role_name AS role_name FROM role r", roleRowMapper);
    }
}
