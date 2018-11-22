package file.dao;

import java.util.List;

import file.entity.Role;

public interface RoleDao extends Dao<Role> {
    
    public List<Role> findAll();
    
}
