package file.service;

import file.entity.Group;
import file.entity.Ware;
import java.util.List;

public interface WareService {

	List<Ware> getAll();

	Ware getWare(String name);
	
	List<Ware> getWare(Group group);

	Ware getWare(Long id);
	
	void addWare(Ware ware);

	void deleteWare(Long id);

	void editWare(Ware ware);

}
