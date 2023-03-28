package learn.capstone.data;

import learn.capstone.models.AppUser;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AppUserJdbcTemplateRepository {
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<AppUser> mapper = (resultSet, index) -> {
        AppUser user = new AppUser();
        user.setAppUserId(resultSet.getInt("app_user_id"));
        user.setUsername(resultSet.getString("username"));
        user.setPassword(resultSet.getString("password_hash"));
        return user;
    };

    public AppUserJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public AppUser findByUsername(String username) {

        String sql = "select app_user_id, username, password_hash from app_user where username = ?;";
        AppUser user = jdbcTemplate.query(sql, mapper, username).stream()
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
}
