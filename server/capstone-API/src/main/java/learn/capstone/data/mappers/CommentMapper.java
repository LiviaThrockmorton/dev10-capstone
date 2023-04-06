package learn.capstone.data.mappers;

import learn.capstone.models.Comment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class CommentMapper implements RowMapper<Comment> {

    @Override
    public Comment mapRow(ResultSet resultSet, int i) throws SQLException {
        Comment comment = new Comment();
        comment.setCommentId(resultSet.getInt("comment_id"));
        comment.setUserId(resultSet.getInt("app_user_id"));

        comment.setContent(resultSet.getString("content"));
        comment.setOutfitId(resultSet.getInt("outfit_id"));
        Timestamp sqlDate = resultSet.getTimestamp("date_time");
        comment.setDateTime(sqlDate.toLocalDateTime());
        comment.setHidden(resultSet.getBoolean("hidden"));
        return comment;
    }
}
