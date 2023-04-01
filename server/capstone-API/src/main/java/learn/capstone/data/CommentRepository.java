package learn.capstone.data;

import learn.capstone.models.Comment;

import java.util.List;

public interface CommentRepository {
    Comment findById(int commentId);

    List<Comment> findByOutfit(int outfitId);

    List <Comment> findByHidden();


    Comment add(Comment comment);

    boolean update(Comment comment);

    boolean deleteById(int commentId);
}
