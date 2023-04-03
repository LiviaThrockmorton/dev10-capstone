package learn.capstone.data;

import learn.capstone.models.AppUser;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Collection;
import java.util.List;

@Repository
public class AppUserRepository {

    private final JdbcTemplate jdbcTemplate;

    public AppUserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public AppUser findByUsername(String username) {
        List<String> roles = getRolesByUsername(username);

        final String sql = "select app_user_id, username, password_hash, email, hidden, enabled "
                + "from app_user "
                + "where username = ? and hidden = 0;";

        return jdbcTemplate.query(sql, new learn.capstone.data.AppUserMapper(roles), username)
                .stream()
                .findFirst().orElse(null);
    }

    @Transactional
    public AppUser create(AppUser user) {

        final String sql = "insert into app_user (username, email, password_hash) values (?, ?);";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        user.setAppUserId(keyHolder.getKey().intValue());
        updateAuthorities(user);
        return user;
    }

    @Transactional
    public void update(AppUser appUser) {

        final String sql = "update app_user set "
                + "username = ?, "
                + "email = ?, "
                + "hidden = ?, "
                + "where app_user_id = ?";

        jdbcTemplate.update(sql,
                appUser.getUsername(), appUser.getEmail(), appUser.getHidden(), appUser.isEnabled(), appUser.getAppUserId());

        updateAuthorities(appUser);
    }

    private void updateAuthorities(AppUser user) {
        jdbcTemplate.update("delete from app_user_role where app_user_id = ?;", user.getAppUserId());
        Collection<GrantedAuthority> authorities = user.getAuthorities();

        if (authorities == null) {
            return;
        }

        for (GrantedAuthority role : authorities) {
            String sql = "insert into app_user_role (app_user_id, app_role_id) "
                    + "select ?, app_role_id from app_role where `name` = ?;";
            jdbcTemplate.update(sql, user.getAppUserId(), role.getAuthority());
        }
    }

    private List<String> getRolesByUsername(String username) {
        final String sql = "select r.name "
                + "from app_user_role ur "
                + "inner join app_role r on ur.app_role_id = r.app_role_id "
                + "inner join app_user au on ur.app_user_id = au.app_user_id "
                + "where au.username = ?";
        return jdbcTemplate.query(sql, (rs, rowId) -> rs.getString("name"), username);
    }


    //
//    @Override
//    public List<AppUser> findAll() {
//        final String sql = "select app_user_id, username, password_hash, email, hidden from app_user where hidden = 0;";
//        return jdbcTemplate.query(sql, new AppUserMapper());
//    }
//
//    @Override
//    public AppUser findById(int appUserId) {
//        String sql = "select app_user_id, username, password_hash, email, hidden from app_user where app_user_id = ? and hidden = 0;";
//        AppUser user = jdbcTemplate.query(sql, new AppUserMapper(), appUserId).stream()
//                .findFirst().orElse(null);
//
//        if (user != null) {
//            addAuthorities(user);
//        }
//        return user;
//    }
//    private void addAuthorities(AppUser user) {
//
//        String sql = "select a.name "
//                + "from app_user_authority aua "
//                + "inner join app_authority a on aua.app_authority_id = a.app_authority_id "
//                + "where aua.app_user_id = ?";
//
//        List<String> authorities = jdbcTemplate.query(
//                sql,
//                (rs, i) -> rs.getString("name"),
//                user.getAppUserId()
//        );
//        user.addAuthorities(authorities);
//    }

}
