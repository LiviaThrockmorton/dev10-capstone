package learn.capstone.data;

import learn.capstone.data.mappers.AppUserMapper;
import learn.capstone.models.AppUser;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class AppUserJdbcTemplateRepository implements AppUserRepository{
    private final JdbcTemplate jdbcTemplate;


    public AppUserJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public AppUser findByUsername(String username) {

        String sql = "select app_user_id, username, password_hash, email, hidden from app_user where username = ?;";
        AppUser user = jdbcTemplate.query(sql, new AppUserMapper(), username).stream()
                .findFirst().orElse(null);

        if (user != null) {
            addAuthorities(user);
        }
        return user;
    }

    private void addAuthorities(AppUser user) {

        String sql = "select a.name "
                + "from app_user_authority aua "
                + "inner join app_authority a on aua.app_authority_id = a.app_authority_id "
                + "where aua.app_user_id = ?";

        List<String> authorities = jdbcTemplate.query(
                sql,
                (rs, i) -> rs.getString("name"),
                user.getAppUserId()
        );
        user.addAuthorities(authorities);
    }



    @Override
    public List<AppUser> findAll() {
        final String sql = "select app_user_id, username, password_hash, email, hidden from app_user;";
        return jdbcTemplate.query(sql, new AppUserMapper());
    }

    @Override
    public AppUser findById(int appUserId) {
        String sql = "select app_user_id, username, password_hash, email, hidden from app_user where app_user_id = ?;";
        AppUser user = jdbcTemplate.query(sql, new AppUserMapper(), appUserId).stream()
                .findFirst().orElse(null);

        if (user != null) {
            addAuthorities(user);
        }
        return user;
    }


    @Override
    public AppUser add(AppUser appUser) {

        final String sql = "insert into appUser (username, email, hidden, enabled) values (?,?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, appUser.getUsername());
            ps.setString(2, appUser.getEmail());
            ps.setBoolean(3,appUser.getHidden());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        appUser.setAppUserId(keyHolder.getKey().intValue());
        return appUser;
    }

    @Override
    public boolean update(AppUser appUser) {

        final String sql = "update app_user set "
                + "username = ?, "
                + "email = ?, "
                + "hidden = ?"
                + "where app_user_id = ?";

        return jdbcTemplate.update(sql, appUser.getUsername(), appUser.getEmail(), appUser.getHidden(), appUser.getAppUserId()) > 0;
    }

    @Override
    @Transactional
    public boolean deleteById(int appUserId) {
        jdbcTemplate.update("delete from comment where app_user_id = ?", appUserId);
        jdbcTemplate.update("delete from outfit where app_user_id = ?", appUserId);
        return jdbcTemplate.update("delete from app_user where app_user_id = ?", appUserId) > 0;
    }

}
