package file.service;

import file.entity.Cart;

public interface CartService {
    
    Cart getCart(Long cid);

    public void totalPrice(Cart c);
}
