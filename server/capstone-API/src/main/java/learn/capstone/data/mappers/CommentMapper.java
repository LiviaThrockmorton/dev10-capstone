package learn.capstone.data.mappers;

import learn.capstone.models.Comment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentMapper implements RowMapper<Comment> {

    @Override
    public Comment mapRow(ResultSet resultSet, int i) throws SQLException {
        Comment comment = new Comment();
        comment.setCommentId(resultSet.getInt("comment_id"));
        comment.setUserId(resultSet.getString("duckImage"));
        comment.setContent(resultSet.getString("content"));
        comment.setOutfitId(resultSet.getString("outfitId"));
        comment.setDateTime(resultSet.getString("dateTime"));
        return comment;
    }
}
