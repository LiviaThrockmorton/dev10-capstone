//package learn.capstone.data;
//
//import learn.capstone.models.UserOutfit;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public class UserOutfitJdbcTemplateRepository implements UserOutfitRepository {
//
//    private final JdbcTemplate jdbcTemplate;
//
//    public UserOutfitJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    @Override
//    public boolean add(UserOutfit appUserOutfit) {
//
//        final String sql = "insert into user_outfit (app_user_id, outfit_id) values "
//                + "(?,?);";
//
//        return jdbcTemplate.update(sql,
//                appUserOutfit.getAppUserId(),
//                appUserOutfit.getOutfit().getOutfitId()) > 0;
//    }
//
//    @Override
//    public boolean update(UserOutfit appUserOutfit) {
//
//        final String sql = "update user_outfit set "
//                + "where app_user_id = ? and outfit_id = ?;";
//
//        return jdbcTemplate.update(sql,
//                appUserOutfit.getAppUserId(),
//                appUserOutfit.getOutfit().getOutfitId()) > 0;
//
//    }
//
//    @Override
//    public boolean deleteByKey(int appUserId, int outfitId) {
//
//        final String sql = "delete from user_outfit "
//                + "where app_user_id = ? and outfit_id = ?;";
//
//        return jdbcTemplate.update(sql, appUserId, outfitId) > 0;
//    }
//}
