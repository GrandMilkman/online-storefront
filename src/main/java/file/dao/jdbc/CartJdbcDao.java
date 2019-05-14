package file.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;

import file.dao.CartDao;
import file.entity.Cart;
import file.entity.User;
import file.entity.Ware;

@Component
public class CartJdbcDao extends JdbcDaoSupport implements CartDao {
    
    @Autowired
    public void setDs(DataSource dataSource) {
         setDataSource(dataSource);
    }
    
//    @Autowired
//    public void setJT(JdbcTemplate jdbcTemplate) {
//         setJdbcTemplate(jdbcTemplate);
//    }
    
    private CartRowMapper crm;
    
    public void setCartRowMapper(CartRowMapper crm) {
        this.crm = crm;
    }
    
    private WareRowMapper wrm;
    
	private ResultSetExtractor<Cart> extractor = new ResultSetExtractor<Cart>() {

		public Cart extractData(final ResultSet rs) throws SQLException, DataAccessException {
			Cart cart = new Cart();
			int count = 0;
			
			while (rs.next()) {
				if (cart == null || !Long.valueOf(rs.getLong("user_id")).equals(cart.getId())) {
					cart = crm.mapRow(rs, count);
					cart.setUser(new User());
					cart.setWares(new ArrayList<Ware>());
				}
				cart.getWares().add(wrm.mapRow(rs, count));
				count++;
			}

			return cart;
		}
	};

	@Override
	public Cart findCart(final Long cid) {
		return getJdbcTemplate().query("SELECT c.cart_id AS cart_id FROM cart c"
				+ "LEFT JOIN cart_user cu ON ur.cart_id = c.cart_id" + "LEFT JOIN user u ON cu.user_id = u.user_id"
				+ "LEFT JOIN user_ware uw ON uw.user_id = cu.user_id" + "LEFT JOIN ware w ON uw.ware_id = w.ware_id",
				extractor);
	}

}
