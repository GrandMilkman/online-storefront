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

import file.dao.WareDao;
import file.entity.Group;
import file.entity.Ware;

public class WareJdbcDao extends JdbcDaoSupport implements WareDao {

    private ResultSetExtractor<List<Ware>> extractor = new ResultSetExtractor<List<Ware>>() {

        public List<Ware> extractData(final ResultSet rs) throws SQLException, DataAccessException {
            final List<Ware> w = new ArrayList<Ware>();
            int count = 0;
            Ware ware = null;
            WareRowMapper wrw = new WareRowMapper();
            GroupRowMapper grw = new GroupRowMapper();
            while (rs.next()) {
                if (ware == null || !Long.valueOf(rs.getLong("ware_id")).equals(ware.getId())) {
                    ware = wrw.mapRow(rs, count);
                    ware.setGroups(new ArrayList<Group>());
                    w.add(ware);
                }
                ware.getGroups().add(grw.mapRow(rs, count));
                count++;
            }
            return w;
        }
    };

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
    public List<Ware> getAll() {

        return getJdbcTemplate().query("SELECT w.ware_id AS ware_id, w.ware_name AS ware_name,"
                + "w.ware_price AS ware_price, w.ware_groupid AS ware_groupid FROM ware w", extractor);
    }

    @Override
    public Ware findById(Long wid) {

        final List<Ware> w = getJdbcTemplate().query(
                "SELECT w.ware_id AS ware_id, w.ware_name AS ware_name,"
                        + "w.ware_price AS ware_price, ware_groupid AS ware_groupid FROM ware w WHERE ware_id = ?",
                extractor, wid);

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

}
