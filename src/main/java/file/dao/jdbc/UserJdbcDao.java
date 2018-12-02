package file.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ArgumentPreparedStatementSetter;
import org.springframework.jdbc.core.ParameterDisposer;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import file.dao.UserDao;
import file.entity.Cart;
import file.entity.Role;
import file.entity.User;

@Component
public class UserJdbcDao extends JdbcDaoSupport implements UserDao{

    private ResultSetExtractor<List<User>> extractor = new ResultSetExtractor<List<User>>() {
        
        public List<User> extractData(final ResultSet rs) throws SQLException, DataAccessException {
            final List<User> u = new ArrayList<User>();
            int count = 0;
            User user = null;
            UserRowMapper urw = new UserRowMapper();
            RoleRowMapper rrw = new RoleRowMapper();
            CartRowMapper crw = new CartRowMapper();
            while (rs.next()) {
                if (user == null || !Long.valueOf(rs.getLong("user_id")).equals(user.getId())) {
                    user = urw.mapRow(rs, count);
                    user.setRoles(new ArrayList<Role>());
                    user.setCart(new Cart());
                    u.add(user);
                }
                user.getRoles().add(rrw.mapRow(rs, count));
                count++;
            };
            return u;
        }
    };

    public void setExtractor(ResultSetExtractor<List<User>> extractor) {
        this.extractor = extractor;
    }
    
    @Override
    public void create(final User u) {
        
        final KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        
        getJdbcTemplate().update(new PreparedStatementCreator() {
            
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                
                final PreparedStatement ps = con.prepareStatement("INSERT INTO user (user_name, user_password VALUES (?, md5(?))",
                        PreparedStatement.RETURN_GENERATED_KEYS);
                final PreparedStatementSetter pss = new ArgumentPreparedStatementSetter(
                        new Object[] {
                                u.getName(), u.getPassword()
                                });
                try {
                    if(pss != null) {
                        pss.setValues(ps);
                    }
                } finally {
                    if (pss instanceof ParameterDisposer) {
                        ((ParameterDisposer) pss).cleanupParameters();
                    }
                }
                return ps;
                
            }
        }, generatedKeyHolder);
        
        u.setId(generatedKeyHolder.getKey().longValue());
        
        getJdbcTemplate().update("INSERT INTO user_role (user_id, role_id) VALUES (?, 1)", u.getId());
    
    }

    @Override
    public User read(final Long uid) {

        final List<User> u = getJdbcTemplate().query("SELECT u.user_id AS user_id, u.user_name AS user_name,"
                + "u.user_password AS user_password FROM user u"
                + "LEFT JOIN user_role ur ON ur.user_id = u.user_id"
                + "LEFT JOIN role r ON r.role_id = ur.role_id WHERE u.user_id = ?",
                extractor, uid);
        return u.get(0);
    }

    @Override
    public User update(final User u) {

        if (u.getPassword() != null) {
            getJdbcTemplate().update("UPDATE user SET user_name = ?, user_password = ? WHERE user_id = ?",
                    u.getName(), u.getPassword(), u.getId());
        } else {
            getJdbcTemplate().update("UPDATE user SET user_name = ? WHERE user_id = ?",
                    u.getName(), u.getId());
        
        }
        return null;
    }

    @Override
    public void delete(final Long uid) {
        
        getJdbcTemplate().update("DELETE FROM user WHERE user_id = ?", uid);
    }

    @Override
    public List<User> findAll() {
        
        return getJdbcTemplate().query("SELECT u.user_id AS user_id, u.user_name AS user_name,"
                + "u.user_password AS user_password FROM user u"
                + "LEFT JOIN user_role ur ON ur.user_id = u.user_id"
                + "LEFT JOIN role r ON r.role_id = ur.role_id WHERE u.user_id > 0",

                extractor);
    }

    @Override
    public User findById(final Long uid) {
        
        final List<User> u = getJdbcTemplate().query("SELECT u.user_id AS user_id, u.user_name AS user_name,"
                + "u.user_password AS user_password FROM user u"
                + "LEFT JOIN user_role ur ON ur.user_id = u.user_id"
                + "LEFT JOIN role r ON r.role_id = ur.role_id WHERE u.user_id = ?",
                extractor, uid);
        
        if (u.size() != 0) {
            return u.get(0);
        
        } else {
            return null;
        }
    }

    @Override
    public User findByName(String name) {
        
        final List<User> u = getJdbcTemplate().query("SELECT u.user_id AS user_id, u.user_name AS user_name,"
                +"u.user_password AS user_password, u.user_roleid AS user_roleid FROM user u"
                + "LEFT JOIN user_role ur ON ur.user_id = u.user_id"
                + "LEFT JOIN role r ON r.role_id = ur.role_id WHERE u.user_name = ?",
                extractor, name);
        
        if (u.size() != 0) {
            return u.get(0);
        
        } else {
            return null;
        }
    }

    /*
    @Override
    public List<User> findByRole(Role r) {
        
        return getJdbcTemplate().query("SELECT u.user_id AS user_id, u.user_name AS user_name,"
                +"u.user_password AS user_password, u.user_roleid AS user_roleid FROM user u WHERE u.user_roleid = ?",
                extractor, r.getId());
    }
    */
}
