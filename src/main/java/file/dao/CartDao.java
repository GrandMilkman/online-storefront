package file.dao;

import file.entity.Cart;

public interface CartDao extends Dao<Cart> {
    
    public Cart getCart(Long cid);
    
    public void update(Cart c);

}
