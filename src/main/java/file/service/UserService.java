package file.service;

import file.entity.Role;
import file.entity.User;
import java.util.List;

public interface UserService {

	void addUser(User user);

	void deleteUserById(Long id);

	void editUser(User user);

	List<User> getAll();

	User getUser(String mail);

	User getUser(Long id);
	
	User getUserByConfirmToken(String token);

	List<Role> getRoles();

}
