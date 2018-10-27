package file.dao;

import java.util.List;

import file.entity.Group;

public interface GroupDao extends Dao<Group, Long> {

    public List<Group> getAll();
    
}
