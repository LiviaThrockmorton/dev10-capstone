package learn.capstone.data;


import learn.capstone.data.mappers.ClothingItemMapper;
import learn.capstone.models.ClothingItem;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class ClothingItemJdbcTemplateRepository implements ClothingItemRepository {

    private final JdbcTemplate jdbcTemplate;

    public ClothingItemJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<ClothingItem> findByType(String itemType) {

        final String sql = "select item_id, item_type, clothing_item_image, hidden "
                + "from clothing_item "
                + "where item_type = ?;";

        return jdbcTemplate.query(sql, new ClothingItemMapper(), itemType);
    }

    @Override
    @Transactional
    public ClothingItem findById(int itemId) {

        final String sql = "select item_id, item_type, clothing_item_image, hidden "
                + "from clothing_item "
                + "where item_id = ?;";

        ClothingItem clothingItem = jdbcTemplate.query(sql, new ClothingItemMapper(), itemId).stream()
                .findFirst().orElse(null);



        return clothingItem;
    }


    @Override
    public ClothingItem add(ClothingItem item) {

        String sql = "insert into clothing_item (item_type, clothing_item_image, hidden) values\n" +
                "(?, ?, ?);";

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

        String sql = "update clothing_item set item_type = ?, clothing_item_image = ?, hidden = ? where item_id = ?;";

        return jdbcTemplate.update(sql,
                item.getItemType(),
                item.getClothingItemImage(),
                item.getHidden(),
                item.getItemId()) > 0;
    }

    public boolean deleteById(int itemId) {
        String sql = "update clothing_item set hidden = 1 where item_id = ?;";
        return jdbcTemplate.update(sql, itemId) > 0;
    }


    @Override
    public int getUsageCount(int itemId) {
        int count = jdbcTemplate.queryForObject(
                "select count(*) from outfit where item_id = ?;", Integer.class, itemId);
        return count;
    }
}