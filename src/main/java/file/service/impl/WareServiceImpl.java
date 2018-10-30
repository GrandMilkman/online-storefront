package file.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import file.dao.GroupDao;
import file.dao.UserDao;
import file.dao.WareDao;
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
	public Ware getByName(String name) {
		return wareDao.findByName(name);
	}

	@Override
	public List<Ware> getByGroup(GroupDao group) {
//????
		return null;
	}

	@Override
	public void addWare(Ware ware) {
		wareDao.create(ware);
	}

	@Override
	public void deleteWare(Long id) {
//?????
	}

	@Override
	public Ware editWare(Ware ware) {
		return wareDao.update(ware);
	}

}
