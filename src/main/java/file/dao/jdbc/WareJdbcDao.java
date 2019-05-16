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

import file.dao.WareDao;
import file.entity.Group;
import file.entity.User;
import file.entity.Ware;

@Component
public class WareJdbcDao extends JdbcDaoSupport implements WareDao {

    @Autowired
    public void setDs(DataSource dataSource) {
        setDataSource(dataSource);
    }

    // @Autowired
    // public void setJT(JdbcTemplate jdbcTemplate) {
    // setJdbcTemplate(jdbcTemplate);
    // }

    @Autowired
    private WareRowMapper wrm;

    @Autowired
    private GroupRowMapper grm;

    public WareRowMapper getWareRowMapper() {
        return wrm;
    }

    public void setWareRowMapper(WareRowMapper wrm) {
        this.wrm = wrm;
    }

    public GroupRowMapper getGroupRowMapper() {
        return grm;
    }

    public void setGroupRowMapper(GroupRowMapper grm) {
        this.grm = grm;
    }

    private ResultSetExtractor<List<Ware>> extractor = new ResultSetExtractor<List<Ware>>() {

        public List<Ware> extractData(final ResultSet rs) throws SQLException, DataAccessException {
            final List<Ware> w = new ArrayList<Ware>();
            int count = 0;
            Ware ware = null;

            while (rs.next()) {
                if (ware == null || !Long.valueOf(rs.getLong("ware_id")).equals(ware.getId())) {
                    ware = wrm.mapRow(rs, count);
                    ware.setGroups(new ArrayList<Group>());
                    w.add(ware);
                }
                ware.getGroups().add(grm.mapRow(rs, count));
                count++;
            }
            ;
            return w;
        }
    };

    public void setExtractor(ResultSetExtractor<List<Ware>> extractor) {
        this.extractor = extractor;
    }

    @Override
    public void create(final Ware w) {

        final KeyHolder generatedKeyHolder = new GeneratedKeyHolder();

        getJdbcTemplate().update(new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {

                final PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO ware (ware_name, ware_price VALUES (?, ?)",
                        PreparedStatement.RETURN_GENERATED_KEYS);
                final PreparedStatementSetter pss = new ArgumentPreparedStatementSetter(
                        new Object[] { w.getName(), w.getPrice() });
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

        w.setId(generatedKeyHolder.getKey().longValue());

    }

    @Override
    public Ware read(Long wid) {

        final List<Ware> w = getJdbcTemplate().query("SELECT w.ware_id AS ware_id, w.ware_name AS ware_name,"
                + "w.ware_price AS ware_price, w.ware_groupid AS ware_groupid FROM ware w", extractor, wid);
        return w.get(0);
    }

    @Override
    public Ware update(Ware w) {

        getJdbcTemplate().update("UPDATE ware SET ware_name = ?, ware_price = ? WHERE ware_id = ?", w.getName(),
                w.getPrice(), w.getId());
        return null;
    }

    @Override
    public void delete(Long wid) {

        getJdbcTemplate().update("DELETE FROM ware WHERE ware_id = ?", wid);
    }

    @Override
    public List<Ware> findAll() {

        return getJdbcTemplate().query("SELECT w.ware_id AS ware_id, w.ware_name AS ware_name,"
                + "w.ware_price AS ware_price, g.group_id AS group_id, g.group_name AS group_name FROM ware w "
                + "LEFT JOIN \"group\" g ON g.group_id = w.ware_groupid", extractor);
    }

    @Override
    public Ware findById(Long wid) {

        final List<Ware> w = getJdbcTemplate().query("SELECT w.ware_id AS ware_id, w.ware_name AS ware_name,"
                + "w.ware_price AS ware_price, g.group_id AS group_id, g.group_name AS group_name FROM ware w "
                + "LEFT JOIN \"group\" g ON g.group_id = w.ware_groupid WHERE ware_id = ?", extractor, wid);

        if (w != null) {
            return w.get(0);
        } else {
            return null;
        }

    }

    @Override
    public Ware findByName(String name) {

        final List<Ware> w = getJdbcTemplate().query(
                "SELECT w.ware_id AS ware_id, w.ware_name AS ware_name,"
                        + "w.ware_price AS ware_price, ware_groupid AS ware_groupid FROM ware w WHERE ware_name = ?",
                extractor, name);

        if (w != null) {
            return w.get(0);
        } else {
            return null;
        }

    }

    @Override
    public List<Ware> findByGroup(Group g) {

        return getJdbcTemplate().query(
                "SELECT w.ware_id AS ware_id, w.ware_name AS ware_name,"
                        + "w.ware_price AS ware_price, ware_groupid AS ware_groupid FROM ware w WHERE ware_groupid = ?",
                extractor, g.getId());
    }

    @Override
    public void setWareToUser(User u, Ware w) {
        getJdbcTemplate().update("INSERT INTO user_ware(user_id, ware_id) VALUES(?, ?)", u.getId(), w.getId());
    }
    
    @Override
    public List<Ware> findUserWare(User u) {
        return getJdbcTemplate().query("SELECT uw.ware_id AS ware_id, w.ware_name AS ware_name, w.ware_price AS ware_price, " 
                + "g.group_id AS group_id, g.group_name AS group_name FROM user_ware uw "
                + "LEFT JOIN ware w ON w.ware_id = uw.ware_id "
                + "LEFT JOIN \"group\" g ON g.group_id = w.ware_groupid WHERE uw.user_id = ?", extractor, u.getId());
    }

}
