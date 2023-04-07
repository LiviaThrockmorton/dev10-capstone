package learn.capstone.data;

import learn.capstone.data.mappers.CommentMapper;
import learn.capstone.data.mappers.AppUserMapper;
import learn.capstone.models.AppUser;
import learn.capstone.models.Comment;
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

        return jdbcTemplate.query(sql, new AppUserMapper(roles), username)
                .stream()
                .findFirst().orElse(null);
    }


    @Transactional
    public AppUser findById(int appUserId) {
        List<String> roles = getRolesById(appUserId);



        final String sql = "select app_user_id, username, password_hash, email, hidden, enabled "
                + "from app_user "
                + "where app_user_id = ? and hidden = 0;";

        return jdbcTemplate.query(sql, new AppUserMapper(roles), appUserId)
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
        jdbcTemplate.update("delete from app_user_authority where app_user_id = ?;", user.getAppUserId());
        Collection<GrantedAuthority> authorities = user.getAuthorities();

        if (authorities == null) {
            return;
        }

        for (GrantedAuthority authority : authorities) {
            String sql = "insert into app_user_authority (app_user_id, app_authority_id) "
                    + "select ?, app_authority_id from app_authority where `name` = ?;";
            jdbcTemplate.update(sql, user.getAppUserId(), authority.getAuthority());
        }
    }

    private List<String> getRolesByUsername(String username) {
        final String sql = "select r.name "
                + "from app_user_authority ur "
                + "inner join app_authority r on ur.app_authority_id = r.app_authority_id "
                + "inner join app_user au on ur.app_user_id = au.app_user_id "
                + "where au.username = ?";
        return jdbcTemplate.query(sql, (rs, rowId) -> rs.getString("name"), username);



    }
    private List<String> getRolesById(int appUserId) {
        final String sql = "select r.name " +
                "from app_user_authority ur " +
                "inner join app_authority r on ur.app_authority_id = r.app_authority_id  " +
                "inner join app_user au on ur.app_user_id = au.app_user_id where au.app_user_id = ?;";

        return jdbcTemplate.query(sql, (rs, rowId) -> rs.getString("name"), appUserId);
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
