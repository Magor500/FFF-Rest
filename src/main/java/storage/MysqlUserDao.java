package storage;

import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;

//TODO: Podľa potreby doplniť metódy

public class MysqlUserDao implements UserDao {

    private JdbcTemplate jdbcTemplate;

    public MysqlUserDao(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> getAll() {
        String sql = "SELECT id_pouzivatelia, krsne, priezvisko, email," +
                " heslo, tel_cislo, mesto, ulica, cislo_domu, registracia, rola from pouzivatel";
        return jdbcTemplate.query(sql, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                long id = rs.getLong("id_pouzivatelia");
                String krsne = rs.getString("krsne");
                String priezvisko = rs.getString("priezvisko");
                String email = rs.getString("email");
                String heslo = rs.getString("heslo");
                String tel_cislo = rs.getString("tel_cislo");
                String mesto = rs.getString("mesto");
                String ulica = rs.getString("ulica");
                String cislo_domu = rs.getString("cislo_domu");
                Date registracia = rs.getDate("registracia");
                int rola = rs.getInt("rola");
                return new User(id, krsne, priezvisko, email, heslo, tel_cislo, mesto, ulica, cislo_domu, registracia, rola);
            }
        });
    }

    @Override
    public User getByEmail(String email) throws EntityNotFoundException {
        String query = "SELECT id_pouzivatelia, krsne, priezvisko, email," +
                " heslo, tel_cislo, mesto, ulica, cislo_domu, registracia, rola from pouzivatel WHERE email = " + "'" + email + "'";
        List<User> users = jdbcTemplate.query(query, userRM());
        return users.get(0);
    }


    @Override
    public User save(User user) throws EntityNotFoundException {
        String salt = BCrypt.gensalt();
        user.setPassword(BCrypt.hashpw(user.getPassword(), salt));
        String sql = "INSERT INTO pouzivatel (krsne, priezvisko, email, heslo, tel_cislo, mesto, ulica, cislo_domu, registracia, rola) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @NotNull
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, user.getName());
                statement.setString(2, user.getSurname());
                statement.setString(3, user.getEmail());
                statement.setString(4, user.getPassword());
                statement.setString(5, user.getTelNum());
                statement.setString(6, user.getCity());
                statement.setString(7, user.getStreet());
                statement.setString(8, user.getHouseNum());
                statement.setDate(9, Date.valueOf(LocalDate.now()));
                statement.setInt(10, user.getRole());
                return statement;
            }
        }, keyHolder);
        long id = Objects.requireNonNull(keyHolder.getKey()).longValue();
        User saved = User.clone(user);
        saved.setId(id);
        return saved;
    }

    public User update(User user) throws EntityNotFoundException {
        String sql = "UPDATE pouzivatel SET krsne = ?, priezvisko = ?, email = ?, heslo = ?, tel_cislo = ?," +
                " mesto = ?, ulica = ?, cislo_domu = ?, registracia = ?, rola = ? WHERE id_pouzivatelia = ?";
        int count = jdbcTemplate.update(sql, user.getName(), user.getSurname(),
                user.getEmail(), user.getPassword(), user.getTelNum(), user.getCity(),
                user.getStreet(), user.getHouseNum(), user.getRegistration(), user.getRole(), user.getId());
        if (count == 0) {
            throw new EntityNotFoundException(
                    "User with id " + user.getId() + " not found");
        }
        return user;
    }

    @Override
    public void delete(long id) throws EntityNotFoundException {
        String query = "DELETE FROM pouzivatel WHERE id_pouzivatelia = ?";
        int count = jdbcTemplate.update(query, id);
        if (count == 0) {
            throw new EntityNotFoundException(
                    "Student with id " + id + " not found");
        }
    }

    @Override
    public User getById(long id) throws EntityNotFoundException {
        String query = "SELECT id_pouzivatelia, krsne, priezvisko, email," +
                " heslo, tel_cislo, mesto, ulica, cislo_domu, registracia," +
                " rola from pouzivatel WHERE id_pouzivatelia = " + id;
        List<User> users = jdbcTemplate.query(query, userRM());
        return users.get(0);
    }

    private RowMapper<User> userRM() {

        return new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();
                long id = rs.getLong("id_pouzivatelia");
                String name = rs.getString("krsne");
                String surname = rs.getString("priezvisko");
                String email = rs.getString("email");
                String pass = rs.getString("heslo");
                String num = rs.getString("tel_cislo");
                String city = rs.getString("mesto");
                String street = rs.getString("ulica");
                String houseNum = rs.getString("cislo_domu");
                Date reg = rs.getDate("registracia");
                int role = rs.getInt("rola");
                return new User(id, name, surname, email, pass, num, city, street, houseNum, reg, role);
            }
        };
    }
}
