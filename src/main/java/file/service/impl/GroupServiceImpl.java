package file.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import file.dao.GroupDao;
import file.entity.Group;
import file.service.GroupService;

@Component
public class GroupServiceImpl implements GroupService {

	@Autowired
	private GroupDao groupDao;
	
	public GroupDao getGroupDao() {
        return groupDao;
    }

    public void setGroupDao(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    @Override
	public List<Group> getAll() {
		return groupDao.findAll();
	}

	@Override
	public void addGroup(Group group) {
		 groupDao.create(group);
	}

	@Override
	public void editGroup(Group group) {
		 groupDao.update(group);
	}

	

}
