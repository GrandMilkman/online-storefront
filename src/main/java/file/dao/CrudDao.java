package file.dao;

import java.io.Serializable;

public interface CrudDao<TYPE, PK extends Serializable> extends Dao<TYPE>{
    
    void create(TYPE t);
    
    TYPE read(PK id);
    
    TYPE update(TYPE t);
    
    void delete(PK id);
}
