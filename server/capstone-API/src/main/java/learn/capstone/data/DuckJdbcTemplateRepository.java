package learn.capstone.data;

import learn.capstone.data.mappers.DuckMapper;
import learn.capstone.models.Duck;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class DuckJdbcTemplateRepository implements DuckRepository {

    private final JdbcTemplate jdbcTemplate;

    public DuckJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Duck> findAll() {

        final String sql = "select duck_id, duck_image, hidden" +
                " from duck;";


        return jdbcTemplate.query(sql, new DuckMapper());
    }

    @Override
    public Duck findById(int duckId) {

        final String sql = "select duck_id, duck_image, hidden from duck"
                + " where duck_id = ?;";

        return jdbcTemplate.query(sql, new DuckMapper(), duckId)
                .stream()
                .findFirst().orElse(null);
    }

    @Override
    public Duck add(Duck duck) {
        final String sql = "insert into duck (duck_image, hidden) " +
                "values (?, ?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, duck.getDuckImage());
            ps.setBoolean(2, duck.getHidden());


            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        duck.setDuckId(keyHolder.getKey().intValue());
        return duck;
    }



    @Override
    public boolean update(Duck duck) {


        final String sql = "update duck " +
                "set duck_image = ?, " +
                "hidden = ? " +
                "where duck_id = ?;";

        return jdbcTemplate.update(sql,
                duck.getDuckImage(),
                duck.getHidden(),
                duck.getDuckId()) > 0;
    }

    @Override
    public boolean deleteById(int duckId) {
        return jdbcTemplate.update("delete from duck where duck_id = ?;", duckId) > 0;
    }

    @Override
    public int getUsageCount(int duckId) {
        int count = jdbcTemplate.queryForObject(
                "select count(*) from outfit where duck_id = ?;", Integer.class, duckId);
        return count;
    }


}
