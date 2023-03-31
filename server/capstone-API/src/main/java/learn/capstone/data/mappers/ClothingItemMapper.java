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
        item.setItemType(resultSet.getString("`type`"));
        item.setClothingItemImage(resultSet.getString("clothing_item_image"));
        item.setHidden(resultSet.getBoolean("hidden"));
        return item;
    }
}
