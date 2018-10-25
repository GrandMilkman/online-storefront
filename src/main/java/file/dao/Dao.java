package file.dao;

import java.io.Serializable;

public interface Dao<TYPE, PK extends Serializable> {
    
    TYPE create(TYPE t);
    
    TYPE read(PK id);
    
    TYPE update(PK id);
    
    void delete(PK id);
}
