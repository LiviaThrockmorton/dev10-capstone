//package learn.capstone.data.mappers;
//
//import learn.capstone.models.Duck;
//import org.springframework.jdbc.core.RowMapper;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class DuckMapper implements RowMapper<Duck> {
//
//    @Override
//    public Duck mapRow(ResultSet resultSet, int i) throws SQLException {
//        Duck duck = new Duck();
//        duck.setDuckId(resultSet.getInt("duck_id"));
//        duck.setDuckImage(resultSet.getString("duck_duckImage"));
//        return duck;
//    }
//}
