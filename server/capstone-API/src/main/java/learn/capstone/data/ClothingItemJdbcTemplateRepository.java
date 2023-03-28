package learn.capstone.data;


import learn.capstone.models.ClothingItem;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class ClothingItemJdbcTemplateRepository implements ClothingItemRepository {

    private final JdbcTemplate jdbcTemplate;

    public ClothingItemJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public ClothingItem add(ClothingItem item) {

        String sql = "insert into item (`item_type`, clothingItemImage, outfit_id) " +
                "values (?,?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        int rowsAffected = jdbcTemplate.update(conn -> {
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, item.getItemType());
            statement.setString(2, item.getClothingItemImage());
            statement.setBoolean(3, item.getHidden());
            return statement;
        }, keyHolder);

        if (rowsAffected > 0) {
            item.setItemId(keyHolder.getKey().intValue());
            return item;
        }

        return null;
    }

    public boolean update(ClothingItem item) {

        String sql = "update item set " +
                "`item_type` = ?, " +
                "clothingItemImage = ? " +
                "where item_id = ?;";

        return jdbcTemplate.update(sql,
                item.getItemType(),
                item.getClothingItemImage(),
                item.getItemId()) > 0;
    }

    public boolean deleteById(int itemId) {
        String sql = "delete from item where item_id = ?;";
        return jdbcTemplate.update(sql, itemId) > 0;
    }
}
