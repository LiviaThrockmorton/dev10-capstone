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
import java.sql.Types;
import java.util.List;

@Repository
public class ClothingItemJdbcTemplateRepository implements ClothingItemRepository {

    private final JdbcTemplate jdbcTemplate;

    public ClothingItemJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }



    @Override
    public List<ClothingItem> findByType(String itemType) {

        final String sql = "";

        return jdbcTemplate.query(sql, new ClothingItemMapper(), itemType);
    }

    @Override
    @Transactional
    public ClothingItem findById(int itemId) {

        final String sql = "select item_id, item_type, clothing_item_image, hidden "
                + "from clothing_items "
                + "where item_id = ?;";

        ClothingItem clothingItem = jdbcTemplate.query(sql, new ClothingItemMapper(), itemId).stream()
                .findFirst().orElse(null);


        return clothingItem;
    }



    @Override
    public ClothingItem add(ClothingItem item) {


        //set 3 ids to zero

        int shirtId = 0;
        int pantsId = 0;
        int hatId = 0;


        if (item.getItemType().equalsIgnoreCase("shirt")) {
            String sql = "insert into shirt (item_type, clothing_item_image, hidden) values (?, ?, ?)";

            KeyHolder keyHolder = new GeneratedKeyHolder();

            int rowsAffected = jdbcTemplate.update(conn -> {
                PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, item.getItemType());
                statement.setString(2, item.getClothingItemImage());
                statement.setBoolean(3, item.getHidden());
                return statement;
            }, keyHolder);

            if (rowsAffected > 0) {
                shirtId = (keyHolder.getKey().intValue());
                ;
            }
        }

        //repeat for pants and hat here
        if (item.getItemType().equalsIgnoreCase("pants")) {
            String sql = "insert into pants (item_type, clothing_item_image, hidden) values (?, ?, ?)";

            KeyHolder keyHolder = new GeneratedKeyHolder();

            int rowsAffected = jdbcTemplate.update(conn -> {
                PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, item.getItemType());
                statement.setString(2, item.getClothingItemImage());
                statement.setBoolean(3, item.getHidden());
                return statement;
            }, keyHolder);

            if (rowsAffected > 0) {
                pantsId = (keyHolder.getKey().intValue());
                ;
            }
        }
        if (item.getItemType().equalsIgnoreCase("hat")) {
            String sql = "insert into hat (item_type, clothing_item_image, hidden) values (?, ?, ?)";

            KeyHolder keyHolder = new GeneratedKeyHolder();

            int rowsAffected = jdbcTemplate.update(conn -> {
                PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, item.getItemType());
                statement.setString(2, item.getClothingItemImage());
                statement.setBoolean(3, item.getHidden());
                return statement;
            }, keyHolder);

            if (rowsAffected > 0) {
                hatId = (keyHolder.getKey().intValue());
                ;
            }
        }


        String sql = "insert into clothing_item (shirt_id, pants_id, hat_id) values\n" +
                "(?, ?, ?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        int finalShirtId = shirtId;
        int finalPantsId = pantsId;
        int finalHatId = hatId;
        int rowsAffected = jdbcTemplate.update(conn -> {
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            if (finalShirtId <= 0){
                statement.setNull(1, Types.INTEGER);
            }
            else {
                statement.setInt(1, finalShirtId);
            }
            if (finalPantsId <= 0){
                statement.setNull(2, Types.INTEGER);
            }
            else {
                statement.setInt(2, finalPantsId);
            }
            if (finalHatId <= 0){
                statement.setNull(3, Types.INTEGER);
            }
            else {
                statement.setInt(3, finalHatId);
            }

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
        String sql = "delete from clothing_item where item_id = ?;";
        return jdbcTemplate.update(sql, itemId) > 0;
    }
}
