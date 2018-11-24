package file.dao;

import file.entity.Cart;

public interface CartDao extends Dao<Cart> {
    
    public Cart findCart(Long cid);
    
    public void update(Cart c);

}
