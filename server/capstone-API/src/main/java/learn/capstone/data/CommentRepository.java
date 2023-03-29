package learn.capstone.data;

import learn.capstone.models.Comment;

public interface CommentRepository {
    Comment findById(int commentId);

    Comment add(Comment comment);

    boolean update(Comment comment);

    boolean deleteById(int commentId);
}
