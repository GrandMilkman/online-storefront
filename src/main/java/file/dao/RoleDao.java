package file.dao;

import java.util.List;

import file.entity.Role;

public interface RoleDao extends Dao<Role, Long> {
    
    public List<Role> getAll();
    
}
