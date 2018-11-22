package file.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import file.dao.RoleDao;
import file.entity.Role;
import file.service.RoleService;

public class RoleServiceImpl implements RoleService {
    
    @Autowired
    private RoleDao roleDao;
	@Override
	public List<Role> getAll() {
		return roleDao.findAll();
	}

}
