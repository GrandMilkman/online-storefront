package file.dao;

import java.util.List;

import file.entity.Cart;

public interface CartDao extends Dao<Cart> {
    
    public List<Cart> getCart(Long cid);
    
    public void update(Cart c);

}
