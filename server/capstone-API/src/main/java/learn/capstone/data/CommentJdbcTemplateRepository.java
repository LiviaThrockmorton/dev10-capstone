package learn.capstone.data;

import learn.capstone.data.mappers.CommentMapper;
import learn.capstone.data.mappers.OutfitMapper;
import learn.capstone.models.Comment;
import learn.capstone.models.Outfit;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.List;

@Repository
public class CommentJdbcTemplateRepository implements CommentRepository {

    private final JdbcTemplate jdbcTemplate;

    public CommentJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Comment findById(int commentId) {

        final String sql = "select comment_id, app_user_id, content, outfit_id, dateTime, hidden "
                + "from comments "
                + "where comment_id = ? and hidden = 0;";

        return jdbcTemplate.query(sql, new CommentMapper(), commentId).stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Comment> findByOutfit(int outfitId) {
        final String sql = "select comment_id, app_user_id, content, outfit_id, date_time, hidden\n" +
                "from comments " +
                "where outfit_id = ?  and hidden = 0 " +
                "order by date_time desc;";
        return jdbcTemplate.query(sql, new CommentMapper(), outfitId);
    }

    @Override
    public List <Comment> findByHidden() {
        final String sql = "select comment_id, app_user_id, content, outfit_id, date_time, hidden " +
                "from comments " +
                "where hidden = 1;";
        return jdbcTemplate.query(sql, new CommentMapper());
    }

    @Override
    public Comment add(Comment comment) {

        final String sql = "insert into comments (app_user_id, content, outfit_id, date_time, hidden)"
                + "values (?,?,?,?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, comment.getUserId());
            ps.setString(2, comment.getContent());
            ps.setInt(3, comment.getOutfitId());
            ps.setTimestamp(4, Timestamp.valueOf(comment.getDateTime()));
            ps.setBoolean(5, comment.getHidden());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        comment.setCommentId(keyHolder.getKey().intValue());
        return comment;
    }

    @Override
    public boolean update(Comment comment) {

        // don't allow appUser_id updates for now
        final String sql = "update comments set "
                + "app_user_id = ?, "
                + "content = ?, "
                + "outfit_id = ?, "
//                + "date_time = ?, "
                + "hidden = ? "
                + "where comment_id = ?;";

        return jdbcTemplate.update(sql,
                comment.getUserId(),
                comment.getContent(),
                comment.getOutfitId(),
// TODO I don't think we want people to be able to update the time
// --could get confusing because the comment would jump?
//                comment.getDateTime(),
                comment.getHidden(),
                comment.getCommentId()) > 0;
    }

    @Override
    public boolean deleteById(int commentId) {
        return jdbcTemplate.update(
                "delete from comments where comment_id = ?", commentId) > 0;
    }
}


