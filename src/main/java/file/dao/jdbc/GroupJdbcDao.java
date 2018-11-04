package file.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import file.dao.GroupDao;
import file.entity.Group;

public class GroupJdbcDao extends JdbcDaoSupport implements GroupDao {

    private ResultSetExtractor<List<Group>> extractor = new ResultSetExtractor<List<Group>>() {

        public List<Group> extractData(final ResultSet rs) throws SQLException, DataAccessException {
            final List<Group> g = new ArrayList<Group>();
            int count = 0;
            Group group = null;
            GroupRowMapper grw = new GroupRowMapper();
            while (rs.next()) {
                if (group == null || !Long.valueOf(rs.getLong("group_id")).equals(group.getId())) {
                    group = grw.mapRow(rs, count);
                    g.add(group);
                }
                count++;
            }
            return g;
        }
    };

    @Override
    public void create(Group t) {
        // TODO Auto-generated method stub

    }

    @Override
    public Group read(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Group update(Group t) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<Group> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

}
