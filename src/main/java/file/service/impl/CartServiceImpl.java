package file.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import file.dao.CartDao;
import file.entity.Cart;
import file.service.CartService;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartDao cartDao;
    
    @Override
    public Cart getCart(Long cid) {
        return cartDao.findCart(cid);
    }

}
