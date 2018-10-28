package file.dao;

import java.util.List;

import file.entity.Group;

public interface GroupDao extends CrudDao<Group, Long> {

    public List<Group> getAll();
    
}
