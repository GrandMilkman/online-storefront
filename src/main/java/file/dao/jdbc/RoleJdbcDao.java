package file.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;

import file.dao.RoleDao;
import file.entity.Role;

@Component
public class RoleJdbcDao extends JdbcDaoSupport implements RoleDao {
    
    @Autowired
    public void setDs(DataSource dataSource) {
         setDataSource(dataSource);
    }
    
//    @Autowired
//    public void setJT(JdbcTemplate jdbcTemplate) {
//         setJdbcTemplate(jdbcTemplate);
//    }
    
    @Autowired
    private RoleRowMapper roleRowMapper;

    public void setRoleRowMapper(RoleRowMapper roleRowMapper) {
        this.roleRowMapper = roleRowMapper;
    }

    public RoleRowMapper getRoleMapper() {
        return roleRowMapper;
    }

    private ResultSetExtractor<List<Role>> extractor = new ResultSetExtractor<List<Role>>() {

        public List<Role> extractData(ResultSet rs) throws SQLException, DataAccessException {
            final List<Role> r = new ArrayList<Role>();
            int count = 0;
            Role role = null;
            while (rs.next()) {
                if (role == null || !Long.valueOf(rs.getLong("role_id")).equals(role.getId())) {
                    role = roleRowMapper.mapRow(rs, count);
                    r.add(role);
                }
            }
            ;
            return r;
        }
    };

    public void setExtractor(ResultSetExtractor<List<Role>> extractor) {
        this.extractor = extractor;
    }
    
    @Override
    public List<Role> findAll() {
        return getJdbcTemplate().query("SELECT r.role_id AS role_id, r.role_name AS role_name FROM roles r",
                extractor);
    }
}
