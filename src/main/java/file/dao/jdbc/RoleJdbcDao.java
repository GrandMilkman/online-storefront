package file.dao.jdbc;

import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;

import file.dao.RoleDao;
import file.entity.Role;

@Component
public class RoleJdbcDao extends JdbcDaoSupport implements RoleDao{
    
    
    private RoleRowMapper roleRowMapper;

    @Override
    public List<Role> findAll() {
        
        return getJdbcTemplate().query("SELECT r.role_id AS role_id, r.role_name AS role_name FROM role r", roleRowMapper);
    }
}
