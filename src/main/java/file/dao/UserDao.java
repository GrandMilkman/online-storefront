package file.dao;

import java.util.List;

import file.entity.User;

public interface UserDao extends Dao<User, Long> {
    
    public List<User> findAll();
    
    public List<User> findAllById();
    
    public List<User> findAllByName();
    
    public List<User> findAllByRole();


}
