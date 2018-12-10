package file.dao.jdbc;

import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import file.dao.RoleDao;
import file.entity.Role;

public class RoleJdbcDao extends JdbcDaoSupport implements RoleDao{
    
    
    private RoleRowMapper rrm;
    
    public void setRoleRowMapper(RoleRowMapper rrm) {
        this.rrm = rrm;
    }

    @Override
    public List<Role> findAll() {
        
        return getJdbcTemplate().query("SELECT r.role_id AS role_id, r.role_name AS role_name FROM role r", rrm);
    }
}
