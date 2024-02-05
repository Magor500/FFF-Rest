package storage;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
//TODO: Podľa potreby doplniť metódy

public class MysqlTypeActivityDao implements TypeActivityDao{
    private JdbcTemplate jdbcTemplate;
    private TypeActivityDao typeActivityDao;

    public MysqlTypeActivityDao(JdbcTemplate jdbcTemplate, TypeActivityDao typeActivityDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.typeActivityDao = typeActivityDao;
    }

    @Override
    public List<TypeActivity> getAll() {
        String query = "SELECT id_typ_cinnosti, nazov from typ_cinnost";
        return jdbcTemplate.query(query, typeActivityRM());
    }

    private RowMapper<TypeActivity> typeActivityRM() {
        return new RowMapper<TypeActivity>() {
            @Override
            public TypeActivity mapRow(ResultSet rs, int rowNum) throws SQLException {
                int id = rs.getInt("id_typ_cinnosti");
                String nazov = rs.getString("nazov");
                return new TypeActivity(id, nazov);
            }
        };
    }


    @Override
    public TypeActivity getById(long id){
        String query = "SELECT id_typ_cinnosti, nazov from typ_cinnost WHERE id_typ_cinnosti = " + id;
        List<TypeActivity> typeActivities = jdbcTemplate.query(query, typeActivityRM());
        return typeActivities.get(0);
    }
}
