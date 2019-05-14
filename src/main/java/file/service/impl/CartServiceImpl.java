package file.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import file.dao.CartDao;
import file.entity.Cart;
import file.entity.Ware;
import file.service.CartService;

@Component
public class CartServiceImpl implements CartService {

    @Autowired
    private CartDao cartDao;
    
    public CartDao getCartDao() {
        return cartDao;
    }

    public void setCartDao(CartDao cartDao) {
        this.cartDao = cartDao;
    }

    @Override
    public Cart getCart(Long cid) {
        return cartDao.findCart(cid);
    }
    
    @Override
    public void totalPrice(final Cart c) {

		Double total = 0.0;
		List<Ware> w = c.getWares();

		for (Ware ware : w) {
			total += ware.getPrice();
		}

		c.setTotal(total);
		
	}

}
