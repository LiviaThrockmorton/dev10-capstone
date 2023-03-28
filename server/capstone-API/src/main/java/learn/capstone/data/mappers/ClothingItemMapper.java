package learn.capstone.data.mappers;

import learn.capstone.models.ClothingItem;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClothingItemMapper implements RowMapper<ClothingItem> {
    @Override
    public ClothingItem mapRow(ResultSet resultSet, int i) throws SQLException {
        ClothingItem item = new ClothingItem();
        item.setItemId(resultSet.getInt("item_id"));
        item.setItemType(resultSet.getString("duckImage"));
        item.setClothingItemImage(resultSet.getString("clothingItemImage"));
        item.setHidden(resultSet.getBoolean("outfit_id"));
        return item;
    }
}
