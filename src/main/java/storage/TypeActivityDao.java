package storage;

import java.util.List;

public interface TypeActivityDao {
    List<TypeActivity> getAll();
    TypeActivity getById(long id);
}
