package file.dao;

import java.util.List;

import file.entity.Group;
import file.entity.Ware;

public interface WareDao extends Dao<Ware, Long> {
    
    public List<Ware> getAll();
    
    public Ware findById(Long id);
    
    public Ware findByName(String name);
    
    public List<Ware> findByGroup(Group g);
    
}
