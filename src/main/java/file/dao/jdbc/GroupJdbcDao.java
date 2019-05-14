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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterDisposer;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import file.dao.GroupDao;
import file.entity.Group;

@Component
public class GroupJdbcDao extends JdbcDaoSupport implements GroupDao {
    
    @Autowired
    public void setDs(DataSource dataSource) {
         setDataSource(dataSource);
    }
    
//    @Autowired
//    public void setJT(JdbcTemplate jdbcTemplate) {
//         setJdbcTemplate(jdbcTemplate);
//    }
    
    @Autowired
    private GroupRowMapper grm;

    public GroupRowMapper getGroupRowMapper() {
        return grm;
    }

    public void setGroupRowMapper(GroupRowMapper grm) {
        this.grm = grm;
    }


    private ResultSetExtractor<List<Group>> extractor = new ResultSetExtractor<List<Group>>() {

        public List<Group> extractData(final ResultSet rs) throws SQLException, DataAccessException {
            final List<Group> g = new ArrayList<Group>();
            int count = 0;
            Group group = null;
            while (rs.next()) {
                if (group == null || !Long.valueOf(rs.getLong("group_id")).equals(group.getId())) {
                    group = grm.mapRow(rs, count);
                    g.add(group);
                }
                count++;
            }
            return g;
        }
    };


    @Override
    public void create(Group g) {

        final KeyHolder generatedKeyHolder = new GeneratedKeyHolder();

        getJdbcTemplate().update(new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {

                final PreparedStatement ps = con.prepareStatement("INSERT INTO \"group\" (group_name VALUES (?)",
                        PreparedStatement.RETURN_GENERATED_KEYS);
                final PreparedStatementSetter pss = new ArgumentPreparedStatementSetter(new Object[] { g.getName() });
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

        g.setId(generatedKeyHolder.getKey().longValue());

    }

    @Override
    public Group read(Long gid) {

        final List<Group> g = getJdbcTemplate().query("SELECT g.group_id AS group_id, g.group_name AS group_name FROM \"group\" g", extractor, gid);

        return g.get(0);
    }

    @Override
    public Group update(Group g) {

        getJdbcTemplate().update("UPDATE \"group\" SET group_name = ? WHERE group_id = ?", g.getName(), g.getId());
        return null;
    }

    @Override
    public void delete(Long gid) {

        getJdbcTemplate().update("DELETE FROM \"group\" WHERE group_id = ?", gid);
    }

    @Override
    public List<Group> findAll() {

        return getJdbcTemplate().query("SELECT g.group_id AS group_id, g.group_name AS group_name FROM \"group\" g", extractor);
    }

}
