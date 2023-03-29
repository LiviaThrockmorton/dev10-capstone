package learn.capstone.data;

import learn.capstone.data.mappers.CommentMapper;
import learn.capstone.models.Comment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class CommentJdbcTemplateRepository implements learn.capstone.data.CommentRepository {

    private final JdbcTemplate jdbcTemplate;

    public CommentJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Comment findById(int commentId) {

        final String sql = "select comment_id, userId, content, outfitId, dateTime"
                + "from comment "
                + "where comment_id = ?;";

        return jdbcTemplate.query(sql, new CommentMapper(), commentId).stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public Comment add(Comment comment) {

        final String sql = "insert into comment (userId, content, outfitId, dateTime)"
                + "values (?,?,?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, comment.getUserId());
            ps.setString(2, comment.getContent());
            ps.setString(3, comment.getOutfitId());
            ps.setString(4, comment.getDateTime());
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
        final String sql = "update comment set "
                + "userId = ?, "
                + "content = ?, "
                + "outfitId = ?, "
                + "dateTime = ?, "
                + "where comment_id = ?;";

        return jdbcTemplate.update(sql,
                comment.getUserId(),
                comment.getContent(),
                comment.getOutfitId(),
                comment.getDateTime(),
                comment.getCommentId()) > 0;
    }

    @Override
    public boolean deleteById(int commentId) {
        return jdbcTemplate.update(
                "delete from comment where comment_id = ?", commentId) > 0;
    }
}
