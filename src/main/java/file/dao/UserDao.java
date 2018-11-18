package file.dao;

import java.util.List;

import file.entity.User;

public interface UserDao extends CrudDao<User, Long> {
    
    public List<User> getAll();
    
    public User findById(Long id);
    
    public User findByName(String name);
    
    /*public List<User> findByRole(Role r);*/

}
