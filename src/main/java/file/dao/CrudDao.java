package file.dao;

import java.io.Serializable;

public interface CrudDao<TYPE, PK extends Serializable> extends Dao<TYPE>{
    
    TYPE create(TYPE t);
    
    TYPE read(PK id);
    
    TYPE update(PK id);
    
    void delete(PK id);
}
