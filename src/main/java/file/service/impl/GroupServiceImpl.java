package file.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import file.dao.GroupDao;
import file.entity.Group;
import file.service.GroupService;

public class GroupServiceImpl implements GroupService {

	// @Autowired
	// private GroupDao groupDao;

	@Override
	public List<Group> getAll() {

		return this.getAll();
	}

}
