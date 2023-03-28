package learn.capstone.data.mappers;

import learn.capstone.models.UserOutfit;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserOutfitMapper  implements RowMapper<UserOutfit> {

    @Override
    public UserOutfit mapRow(ResultSet resultSet, int i) throws SQLException {

        UserOutfit UserOutfit = new UserOutfit();
        UserOutfit.setAppUserId(resultSet.getInt("appUser_id"));

        OutfitMapper outfitMapper = new OutfitMapper();
        UserOutfit.setOutfit(outfitMapper.mapRow(resultSet, i));

        return UserOutfit;
    }
}
