package learn.capstone.data.mappers;

import learn.capstone.models.Outfit;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OutfitMapper implements RowMapper<Outfit> {

    @Override
    public Outfit mapRow(ResultSet resultSet, int i) throws SQLException {
        Outfit outfit = new Outfit();
        outfit.setOutfitId(resultSet.getInt("outfit_id"));
        outfit.setDuckId(resultSet.getInt("duck_id"));
        outfit.setUser_id(resultSet.getInt("app_user_id"));

        if (resultSet.getDate("date_created") != null) {
            outfit.setDateCreated(resultSet.getDate("date_created").toLocalDate());
        }
        outfit.setShirtId(resultSet.getInt("shirt_id"));
        outfit.setPantsId(resultSet.getInt("pants_id"));
        outfit.setHatId(resultSet.getInt("hat_id"));
        outfit.setPosted(resultSet.getBoolean("posted"));
        outfit.setHidden(resultSet.getBoolean("hidden"));

        return outfit;
    }
}