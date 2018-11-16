package file.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import file.dao.GroupDao;
import file.dao.UserDao;
import file.dao.WareDao;
import file.entity.Group;
import file.entity.Ware;
import file.service.WareService;

public class WareServiceImpl implements WareService {

	@Autowired
	private WareDao wareDao;

	@Override
	public List<Ware> getAll() {
		return this.getAll();
	}

	@Override
	public Ware getWare(String name) {
		return wareDao.findByName(name);
	}

	@Override
	public List<Ware> getWare(Group group) {
		return wareDao.findByGroup(group);
	}

	@Override
	public Ware getWare(Long id) {
		return wareDao.findById(id);
	}

	@Override
	public void addWare(Ware ware) {
		wareDao.create(ware);
	}

	@Override
	public void deleteWare(Long id) {
		wareDao.delete(id);
	}

	@Override
	public void editWare(Ware ware) {
		wareDao.update(ware);
	}

}
