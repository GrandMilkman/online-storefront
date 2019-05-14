package file.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import file.entity.Ware;

@Component
public class WareRowMapper implements RowMapper<Ware> {

    public Ware mapRow(final ResultSet rs,final int rowNum) throws SQLException {

        final Ware w = new Ware();
        w.setId(rs.getLong("ware_id"));
        w.setName(rs.getString("ware_name"));
        w.setPrice(rs.getDouble("ware_price"));
        return w;
    }

}
