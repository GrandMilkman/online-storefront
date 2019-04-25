package file.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import file.dao.RoleDao;
import file.dao.UserDao;
import file.entity.Role;
import file.entity.User;
import file.service.UserService;

public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
	private RoleDao roleDao;

	public RoleDao getRoleDao() {
        return roleDao;
    }

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
	public void addUser(User user) {
		userDao.create(user);
	}

	@Override
	public void deleteUserById(Long id) {
		userDao.delete(id);
	}

	@Override

	public void editUser(User user) {
		 userDao.update(user);
	}

	@Override
	public List<User> getAll() {
		return userDao.findAll();
	}

	@Override
	public User getUser(String mail) {
		return userDao.findByMail(mail);
	}

	@Override
	public User getUser(Long id) {
		return userDao.findById(id);
	}

	@Override
	public List<Role> getRoles() {
		return roleDao.findAll();
	}
	
}
