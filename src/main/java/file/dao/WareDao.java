package file.dao;

import java.util.List;

import file.entity.Group;
import file.entity.User;
import file.entity.Ware;

public interface WareDao extends CrudDao<Ware, Long> {

    public List<Ware> findAll();

    public Ware findById(Long id);

    public Ware findByName(String name);

    public List<Ware> findByGroup(Group g);

    public void setWareToUser(User u, Ware w);
    
    public List<Ware> findUserWare(User u);

}
