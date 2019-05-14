package file.dao;

import java.util.List;

import file.entity.User;

public interface UserDao extends CrudDao<User, Long> {

    public List<User> findAll();

    public User findById(Long id);

    public User findByMail(String mail);
    
    public User findByConfirmToken(String token);
    /*public List<User> findByRole(Role r);*/

}
