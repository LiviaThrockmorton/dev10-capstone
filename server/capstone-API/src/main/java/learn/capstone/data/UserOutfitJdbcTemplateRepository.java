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
//        final String sql = "insert into appUser_outfit (appUser_id, outfit_id) values "
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
//        final String sql = "update appUser_outfit set "
//                + "where appUser_id = ? and outfit_id = ?;";
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
//        final String sql = "delete from appUser_outfit "
//                + "where appUser_id = ? and outfit_id = ?;";
//
//        return jdbcTemplate.update(sql, appUserId, outfitId) > 0;
//    }
//}
