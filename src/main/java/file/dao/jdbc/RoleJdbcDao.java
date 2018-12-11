package file.dao.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import file.dao.RoleDao;
import file.entity.Role;

public class RoleJdbcDao extends JdbcDaoSupport implements RoleDao{
    
    @Autowired
    private RoleRowMapper roleRowMapper;
    
    public void setRoleRowMapper(RoleRowMapper roleRowMapper) {
        this.roleRowMapper = roleRowMapper;
    }
    
    public RoleRowMapper getRoleMapper() {
    	return roleRowMapper;
    }

    @Override
    public List<Role> findAll() {
        
        return getJdbcTemplate().query("SELECT r.role_id AS role_id, r.role_name AS role_name FROM roles r", roleRowMapper);
    }
}
