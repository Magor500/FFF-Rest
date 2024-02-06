package storage;

import java.util.List;


public interface UserDao {

    public User update(User user) throws EntityNotFoundException;

    List<User> getAll();
    User getByEmail(String email) throws EntityNotFoundException;

    User save(User user) throws EntityNotFoundException;

    void delete(long id) throws EntityNotFoundException;

    User getById(long idPouzivatelia) throws EntityNotFoundException;
}
