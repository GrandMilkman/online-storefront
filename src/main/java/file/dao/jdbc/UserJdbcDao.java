package file.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.ArgumentPreparedStatementSetter;
import org.springframework.jdbc.core.ParameterDisposer;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import file.dao.UserDao;
import file.entity.Role;
import file.entity.User;

public class UserJdbcDao extends JdbcDaoSupport implements UserDao{

    private ResultSetExtractor<List<User>> extractor;
    
    public void setExtractor(ResultSetExtractor<List<User>> extractor) {
        this.extractor = extractor;
    }
    
    @Override
    public void create(final User u) {
        
        final KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        
        getJdbcTemplate().update(new PreparedStatementCreator() {
            
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                
                final PreparedStatement ps = con.prepareStatement("INSERT INTO user (user_name, user_password VALUES (?, ?)",
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
    
    }

    @Override
    public User read(final Long id) {

        final List<User> u = getJdbcTemplate().query("SELECT u.user_id AS user_id, u.user_name AS user_name,"
                +"u.user_password AS user_password, u.user_roleid AS user_roleid FROM user u",
                extractor, id);
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
    public void delete(final Long id) {
        
        getJdbcTemplate().update("DELETE FROM user WHERE user_id = ?", id);
    }

    @Override
    public List<User> getAll() {
        
        return getJdbcTemplate().query("SELECT u.user_id AS user_id, u.user_name AS user_name,"
                +"u.user_password AS user_password, u.user_roleid AS user_roleid FROM user u", extractor);
    }

    @Override
    public User findById(final Long id) {
        
        final List<User> u = getJdbcTemplate().query("SELECT u.user_id AS user_id, u.user_name AS user_name,"
                +"u.user_password AS user_password, u.user_roleid AS user_roleid FROM user u WHERE u.user_id = ?",
                extractor, id);
        
        if (u.size() != 0) {
            return u.get(0);
        
        } else {
            return null;
        }
    }

    @Override
    public User findByName(String name) {
        
        final List<User> u = getJdbcTemplate().query("SELECT u.user_id AS user_id, u.user_name AS user_name,"
                +"u.user_password AS user_password, u.user_roleid AS user_roleid FROM user u WHERE u.user_name = ?",
                extractor, name);
        
        if (u.size() != 0) {
            return u.get(0);
        
        } else {
            return null;
        }
    }

    @Override
    public List<User> findByRole(Role r) {
        
        return getJdbcTemplate().query("SELECT u.user_id AS user_id, u.user_name AS user_name,"
                +"u.user_password AS user_password, u.user_roleid AS user_roleid FROM user u WHERE u.user_roleid = ?",
                extractor, r.getId());
    }
}
