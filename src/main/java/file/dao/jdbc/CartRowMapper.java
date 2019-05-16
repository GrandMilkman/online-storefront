package file.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import file.entity.Cart;

@Component
public class CartRowMapper implements RowMapper<Cart> {

    @Override
    public Cart mapRow(ResultSet rs, int rowNum) throws SQLException {

        final Cart c = new Cart();
        c.setId(rs.getLong("cart_id"));
        return c;
    }

}
