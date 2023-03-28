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
import java.util.List;

@Repository
public class OutfitJdbcTemplateRepository implements OutfitRepository {

    private final JdbcTemplate jdbcTemplate;

    public OutfitJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Outfit> findAll() {
        final String sql = "select outfit_id, shirt_id, pants_id, hat_id, date_created, duck_id "
                + "from outfit limit 1000;";
        return jdbcTemplate.query(sql, new OutfitMapper());
    }

    @Override
    @Transactional
    public Outfit findById(int outfitId) {

        final String sql = "select outfit_id, shirt_id, pants_id, hat_id, date_created, duck_id "
                + "from outfit "
                + "where outfit_id = ?;";

        Outfit outfit = jdbcTemplate.query(sql, new OutfitMapper(), outfitId).stream()
                .findFirst().orElse(null);

        if (outfit != null) {

            addClothingItems(outfit);
        }

        return outfit;
    }

    @Override
    public Outfit add(Outfit outfit) {

        final String sql = "insert into outfit (shirt_id, pants_id, hat_id, dateCreated, duck_id) "
                + " values (?,?,?,?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, outfit.getShirtId());
            ps.setInt(2, outfit.getPantsId());
            ps.setInt(3, outfit.getHatId());
            ps.setDate(4, outfit.getDateCreated() == null ? null : Date.valueOf(outfit.getDateCreated()));
            ps.setInt(5, outfit.getDuckId());
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
                + "shirt_id = ?, "
                + "pants_id = ?, "
                + "hat_id = ?, "
                + "dateCreated = ?, "
                + "duck_id = ? "
                + "where outfit_id = ?;";

        return jdbcTemplate.update(sql,
                outfit.getShirtId(),
                outfit.getPantsId(),
                outfit.getHatId(),
                outfit.getDateCreated(),
                outfit.getDuckId(),
                outfit.getOutfitId()) > 0;
    }

    @Override
    @Transactional
    public boolean deleteById(int outfitId) {
        jdbcTemplate.update("delete from appUser_outfit where outfit_id = ?;", outfitId);
        return jdbcTemplate.update("delete from outfit where outfit_id = ?;", outfitId) > 0;
    }


    private void addClothingItems(Outfit outfit) {

        String sql = "select item_id, `duckImage`, clothingItemImage, outfit_id " +
                "from item where outfit_id = ?;";

        var outfitClothingItems = jdbcTemplate.query(sql, new ClothingItemMapper(), outfit.getOutfitId());
        outfit.setItems(outfitClothingItems);
    }
}
