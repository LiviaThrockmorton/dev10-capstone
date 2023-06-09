package learn.capstone.data;


import learn.capstone.data.mappers.OutfitMapper;
import learn.capstone.data.mappers.ClothingItemMapper;
import learn.capstone.models.Outfit;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

@Repository
public class OutfitJdbcTemplateRepository implements OutfitRepository {

    private final JdbcTemplate jdbcTemplate;

    public OutfitJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Outfit> findAll() {
        final String sql = "select outfit_id, " +
                "app_user_id,  " +
                "shirt_id,  " +
                "pants_id,  " +
                "hat_id,  " +
                "date_created,  " +
                "duck_id,  " +
                "posted,  " +
                "hidden  " +
                "from outfit " +
                "where hidden = 0 " +
                "and posted = 1 " +
                "order by date_created asc;";
        return jdbcTemplate.query(sql, new OutfitMapper());
    }

    @Override
    @Transactional
    public Outfit findById(int outfitId) {

        final String sql = "select outfit_id, " +
                "app_user_id,  " +
                "shirt_id,  " +
                "pants_id,  " +
                "hat_id,  " +
                "date_created,  " +
                "duck_id,  " +
                "posted,  " +
                "hidden  " +
                "from outfit " +
                "where outfit_id = ? " +
                "and hidden = 0 " +
                "order by date_created asc;";

        Outfit outfit = jdbcTemplate.query(sql, new OutfitMapper(), outfitId).stream()
                .findFirst().orElse(null);

//        if (outfit != null) {
//
//            addClothingItems(outfit);
//        }

        return outfit;
    }

    @Override
        public List<Outfit> findByUser(int userId) {
            final String sql = "select outfit_id, " +
                    "app_user_id,  " +
                    "shirt_id,  " +
                    "pants_id,  " +
                    "hat_id,  " +
                    "date_created,  " +
                    "duck_id,  " +
                    "posted,  " +
                    "hidden  " +
                    "from outfit " +
                    "where app_user_id = ? " +
                    "and hidden = 0 " +
                    "order by date_created asc;";
            return jdbcTemplate.query(sql, new OutfitMapper(), userId);
        }

    @Override
    public Outfit add(Outfit outfit) {

        final String sql = "insert into outfit (app_user_id, shirt_id, pants_id, hat_id, date_created, duck_id, posted, hidden) "
                + " values (?,?,?,?,?,?,?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, outfit.getUserId());
            ps.setInt(2, outfit.getShirtId());
            ps.setInt(3, outfit.getPantsId());
            ps.setInt(4, outfit.getHatId());
//            ps.setDate(5, outfit.getDateCreated() == null ? null : Date.valueOf(outfit.getDateCreated()));
            ps.setDate(5, Date.valueOf(LocalDate.now()));
            ps.setInt(6, outfit.getDuckId());
            ps.setBoolean(7, outfit.getPosted());
            ps.setBoolean(8, outfit.getHidden());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        outfit.setOutfitId(keyHolder.getKey().intValue());
        return outfit;
    }

    @Override
    public boolean update(Outfit outfit) {

        final String sql = "update outfit set "
                + "duck_id = ?, "
                + "app_user_id = ?, "
                + "date_created = ?, "
                + "shirt_id = ?, "
                + "pants_id = ?, "
                + "hat_id = ?, "
                + "posted = ?, "
                + "hidden = ? "
                + "where outfit_id = ?;";

        return jdbcTemplate.update(sql,
                outfit.getDuckId(),
                outfit.getUserId(),
                outfit.getDateCreated(),
                outfit.getShirtId(),
                outfit.getPantsId(),
                outfit.getHatId(),
                outfit.getPosted(),
                outfit.getHidden(),
                outfit.getOutfitId()) > 0;
    }

    @Override
    @Transactional
    public boolean deleteById(int outfitId) {
        jdbcTemplate.update("update comments set hidden = 1 where outfit_id = ?;", outfitId);
        return jdbcTemplate.update("update outfit set hidden = 1 where outfit_id = ?;", outfitId) > 0;
    }









    private void addClothingItems(Outfit outfit) {

        String sql = "select item_id, item_type, clothing_item_image, hidden " +
                "from item where outfit_id = ?;";

//        var outfitClothingItems = jdbcTemplate.query(sql, new ClothingItemMapper(), outfit.getOutfitId());
//        outfit.setItems(outfitClothingItems);
    }
}
