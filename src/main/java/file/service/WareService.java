package file.service;

import file.dao.GroupDao;
import file.dao.WareDao;
import file.entity.Ware;
import java.util.List;

public interface WareService {

	List<Ware> getAll();

	Ware getByName(String name);

	// List<Ware> getByPrice(?);

	// ??
	List<Ware> getByGroup(GroupDao group);

	void addWare(Ware ware);

	void deleteWare(Long id);

	Ware editWare(Ware ware);

}
