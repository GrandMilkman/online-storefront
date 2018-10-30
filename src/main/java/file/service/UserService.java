package file.service;

import file.dao.UserDao;
import file.entity.User;
import java.util.List;

public interface UserService {
//????
	void addUser(User user);

	void deleteUserById(Long id);

//????
	User editUser(User user);

	List<User> getAll();

	User getUser(String name);

	User getUser(Long id);

}
