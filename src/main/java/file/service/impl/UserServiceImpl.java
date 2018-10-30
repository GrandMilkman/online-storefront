package file.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import file.dao.UserDao;
import file.entity.User;
import file.service.UserService;

public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public void addUser(User user) {
		userDao.create(user);
	}

	@Override
	public void deleteUserById(Long id) {
		// userDao.delete(id);
	}

	@Override
	// ?????
	public User editUser(User user) {
		return userDao.update(user);
	}

	@Override
	public List<User> getAll() {
		return this.getAll();
	}

	@Override
	public User getUser(String name) {
		return userDao.findByName(name);
	}

	@Override
	public User getUser(Long id) {
		return userDao.findById(id);
	}

}
