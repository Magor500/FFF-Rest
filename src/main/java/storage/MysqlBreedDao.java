package storage;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//TODO: Podľa potreby doplniť metódy
public class MysqlBreedDao implements BreedDao {
    private JdbcTemplate jdbcTemplate;

    private static class BreedResultSetExtractor<T> implements ResultSetExtractor<List<Breed>> {

        @Override
        public List<Breed> extractData(ResultSet rs) throws SQLException, DataAccessException {
            List<Breed> breeds = new ArrayList<>();
            Breed breed = null;
            while (rs.next()) {
                long id = rs.getLong("id_plemena");
                if (breed == null || id != breed.getId()) {
                    breed = new Breed();
                    breed.setId(id);
                    breed.setBrand(Breed.Brand.valueOf(rs.getString("druh")));
                    breed.setName(rs.getString("nazov"));
                    breed.setSize(rs.getInt("velkost"));
                }
                breeds.add(breed);
            }
            return breeds;
        }
    }

    public MysqlBreedDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Breed> getAll() {
        String sql = "SELECT id_plemena, druh, nazov, velkost FROM plemeno";
        return jdbcTemplate.query(sql, new MysqlBreedDao.BreedResultSetExtractor<>());
    }

    @Override
    public Breed getById(long id) throws EntityNotFoundException {
        String sql = "SELECT id_plemena, druh, nazov, velkost FROM plemeno WHERE id_plemena=" + id;
        List<Breed> result = jdbcTemplate.query(sql, new BreedResultSetExtractor<>());
        return result != null && result.size() == 1 ? result.get(0) : null;
    }

    @Override
    public Breed save(Breed breed) throws EntityNotFoundException {
        String query = "INSERT INTO plemeno (druh, nazov, velkost) VALUES (?, ?, ?)";
        jdbcTemplate.update(query, breed.getBrand().toString(), breed.getName(), breed.getSize());
        return breed;
    }

    @Override
    public void delete(long id) throws EntityNotFoundException {
        String query = "DELETE FROM plemeno WHERE id_plemena = " + id;
        jdbcTemplate.update(query);
    }
}
