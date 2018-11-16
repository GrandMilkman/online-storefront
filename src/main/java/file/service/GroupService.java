package file.service;

import file.entity.Group;
import java.util.List;

public interface GroupService{

	List<Group> getAll();  

	void addGroup(Group group);
	
	void editGroup(Group group);
	
}
