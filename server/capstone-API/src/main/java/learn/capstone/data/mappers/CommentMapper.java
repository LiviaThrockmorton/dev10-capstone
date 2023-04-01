package learn.capstone.data.mappers;

import learn.capstone.models.Comment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class CommentMapper implements RowMapper<Comment> {

    @Override
    public Comment mapRow(ResultSet resultSet, int i) throws SQLException {
        Comment comment = new Comment();
        comment.setCommentId(resultSet.getInt("comment_id"));
        comment.setUserId(resultSet.getInt("app_user_id"));
        comment.setUserId(resultSet.getInt("outfit_id"));
        comment.setContent(resultSet.getString("content"));
        comment.setOutfitId(resultSet.getInt("outfit_id"));
        comment.setDateTime(LocalDateTime.parse(resultSet.getString("date_time")));
        return comment;
    }
}
