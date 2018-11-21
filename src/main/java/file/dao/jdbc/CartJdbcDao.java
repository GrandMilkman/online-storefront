package file.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import file.dao.CartDao;
import file.entity.Cart;
import file.entity.User;
import file.entity.Ware;

public class CartJdbcDao extends JdbcDaoSupport implements CartDao {

    private ResultSetExtractor<List<Cart>> extractor = new ResultSetExtractor<List<Cart>>() {

        public List<Cart> extractData(final ResultSet rs) throws SQLException, DataAccessException {
            final List<Cart> c = new ArrayList<Cart>();
            int count = 0;
            Cart cart = null;
            CartRowMapper crw = new CartRowMapper();
            UserRowMapper urw = new UserRowMapper();
            WareRowMapper wrw = new WareRowMapper();
            while (rs.next()) {
                if (cart == null || !Long.valueOf(rs.getLong("user_id")).equals(cart.getId())) {
                    cart = crw.mapRow(rs, count);
                    cart.setUser(new User());
                    cart.setWares(new ArrayList<Ware>());
                    c.add(cart);
                }
                cart.getWares().add(wrw.mapRow(rs, count));
                count++;
            }
            ;
            return c;
        }
    };

    @Override
    public List<Cart> getCart(final Long cid) {
        return getJdbcTemplate().query("SELECT c.cart_id AS cart_id FROM cart c"
                + "LEFT JOIN cart_user cu ON ur.cart_id = c.cart_id" + "LEFT JOIN user u ON cu.user_id = u.user_id"
                + "LEFT JOIN user_ware uw ON uw.user_id = cu.user_id" + "LEFT JOIN ware w ON uw.ware_id = w.ware_id",
                extractor);
    }

    @Override
    public void update(final Cart c) {
        
        Double total = 0.0;
        List<Ware> w = c.getWares();
        
        for(Ware ware : w) {
            total += ware.getPrice();
        }
        
        c.setTotal(total);
    }

}
