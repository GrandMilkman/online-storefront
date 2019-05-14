package file.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import file.entity.Group;

public class GroupRowMapper implements RowMapper<Group> {

    @Override
    public Group mapRow(final ResultSet rs,final int rowNum) throws SQLException {

        Group g = new Group();
        g.setId(rs.getLong("group_id"));
        g.setName(rs.getString("group_name"));
        return g;
    }

}
