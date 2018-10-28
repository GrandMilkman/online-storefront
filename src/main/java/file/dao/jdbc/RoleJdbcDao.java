package file.dao.jdbc;

import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import file.dao.RoleDao;
import file.entity.Role;

public class RoleJdbcDao extends JdbcDaoSupport implements RoleDao{

    private RowMapper<Role> rowMapper;

    @Override
    public List<Role> getAll() {
        
        return getJdbcTemplate().query("SELECT id AS id, name AS name FROM role", rowMapper);
    }
}
