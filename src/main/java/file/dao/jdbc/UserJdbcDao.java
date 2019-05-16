package file.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
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
public class UserJdbcDao extends JdbcDaoSupport implements UserDao {

    @Autowired
    public void setDs(DataSource dataSource) {
        setDataSource(dataSource);
    }

    // @Autowired
    // public void setJT(JdbcTemplate jdbcTemplate) {
    // setJdbcTemplate(jdbcTemplate);
    // }

    @Autowired
    private UserRowMapper userRowMapper;

    @Autowired
    private RoleRowMapper roleRowMapper;

    public RoleRowMapper getRoleRowMapper() {
        return roleRowMapper;
    }

    public void setRoleRowMapper(RoleRowMapper roleRowMapper) {
        this.roleRowMapper = roleRowMapper;
    }

    public UserRowMapper getUserRowMapper() {
        return userRowMapper;
    }

    public void setUserRowMapper(UserRowMapper userRowMapper) {
        this.userRowMapper = userRowMapper;
    }

    private ResultSetExtractor<List<User>> extractor = new ResultSetExtractor<List<User>>() {

        public List<User> extractData(ResultSet rs) throws SQLException, DataAccessException {
            final List<User> u = new ArrayList<User>();
            int count = 0;
            User user = null;
            while (rs.next()) {
                if (user == null || !Long.valueOf(rs.getLong("user_id")).equals(user.getId())) {
                    user = userRowMapper.mapRow(rs, count);
                    user.setRoles(new ArrayList<Role>());
                    user.setCart(new Cart());
                    u.add(user);
                }

                user.getRoles().add(roleRowMapper.mapRow(rs, count));
                count++;
            }
            ;
            return u;
        }
    };

    public void setExtractor(ResultSetExtractor<List<User>> extractor) {
        this.extractor = extractor;
    }

    /*
     * @Autowired private DataSource dataSource;
     * 
     * @PostConstruct private void initialize() { setDataSource(dataSource); }
     */
    @Override
    public void create(final User u) {

        final KeyHolder generatedKeyHolder = new GeneratedKeyHolder();

        getJdbcTemplate().update(new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {

                final PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO users (user_name, user_password,user_mail) VALUES (?, md5(?),?)",
                        PreparedStatement.RETURN_GENERATED_KEYS);
                final PreparedStatementSetter pss = new ArgumentPreparedStatementSetter(
                        new Object[] { u.getName(), u.getPassword(), u.getMail() });
                try {
                    if (pss != null) {
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

        getJdbcTemplate().update("INSERT INTO user_role (user_id, role_id) VALUES (?, 2)", u.getId());

    }

    @Override
    public User read(final Long uid) {

        final List<User> u = getJdbcTemplate().query("SELECT u.user_id AS user_id, u.user_name AS user_name,"
                + "u.user_password AS user_password , u.user_mail AS user_mail FROM users u "
                + "LEFT JOIN user_role ur ON ur.user_id = u.user_id "
                + "LEFT JOIN roles r ON r.role_id = ur.role_id WHERE u.user_id = ? ", extractor, uid);
        return u.get(0);
    }

    @Override
    public User update(final User u) {

        if (u.getPassword() != null) {
            getJdbcTemplate().update(
                    "UPDATE users SET user_name = ?,user_mail = ?, user_password = md5(?) WHERE user_id = ? ",
                    u.getName(), u.getMail(), u.getPassword(), u.getId());
        } else {
            getJdbcTemplate().update("UPDATE users SET user_name = ? , user_mail = ? WHERE user_id = ?", u.getName(),
                    u.getMail(), u.getId());

        }
        return null;
    }

    @Override
    public void delete(final Long uid) {
        getJdbcTemplate().update("DELETE FROM user_role WHERE user_id = ?", uid);
        getJdbcTemplate().update("DELETE FROM users WHERE user_id = ?", uid);
    }

    @Override
    public List<User> findAll() {

        return getJdbcTemplate().query("SELECT u.user_id AS user_id, u.user_name AS user_name, "
                + "u.user_password AS user_password,u.user_mail AS user_mail, r.role_id AS role_id, "
                + "r.role_name AS role_name " + "FROM users u " + "LEFT JOIN user_role ur ON ur.user_id = u.user_id "
                + "LEFT JOIN roles r ON r.role_id = ur.role_id WHERE u.user_id >0", extractor);
    }

    @Override
    public User findById(final Long uid) {

        final List<User> u = getJdbcTemplate().query("SELECT u.user_id AS user_id, u.user_name AS user_name, "
                + "u.user_password AS user_password, u.user_mail AS user_mail, r.role_id AS role_id, "
                + "r.role_name AS role_name " + "FROM users u " + "LEFT JOIN user_role ur ON ur.user_id = u.user_id "
                + "LEFT JOIN roles r ON r.role_id = ur.role_id WHERE u.user_id = ? ", extractor, uid);

        if (u.size() != 0) {
            return u.get(0);

        } else {
            return null;
        }
    }

    @Override
    public User findByMail(final String mail) {

        final List<User> u = getJdbcTemplate().query("SELECT u.user_id AS user_id, u.user_name AS user_name, "
                + "u.user_password AS user_password , u.user_mail AS user_mail, r.role_id AS role_id, "
                + "r.role_name AS role_name FROM users u " + "LEFT JOIN user_role ur ON ur.user_id = u.user_id "
                + "LEFT JOIN roles r ON r.role_id = ur.role_id WHERE u.user_mail= ? ", extractor, mail);

        if (u.size() != 0) {
            return u.get(0);

        } else {
            return null;
        }
    }

    /*
     * @Override public List<User> findByRole(Role r) {
     * 
     * return getJdbcTemplate().
     * query("SELECT u.user_id AS user_id, u.user_name AS user_name,"
     * +"u.user_password AS user_password, u.user_roleid AS user_roleid FROM user u WHERE u.user_roleid = ?"
     * , extractor, r.getId()); }
     */
}
