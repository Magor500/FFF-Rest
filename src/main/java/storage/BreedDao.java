package storage;

import java.util.List;

public interface BreedDao {
    List<Breed> getAll();

    Breed getById(long id) throws EntityNotFoundException;
    Breed save(Breed breed) throws EntityNotFoundException;
    void delete(long id) throws EntityNotFoundException;
}
