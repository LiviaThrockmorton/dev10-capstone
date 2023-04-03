package learn.capstone.domain;

import learn.capstone.data.CommentRepository;
import learn.capstone.data.OutfitRepository;
import learn.capstone.models.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository repository;
    private final OutfitRepository outfitRepository;

    public CommentService(CommentRepository repository, OutfitRepository outfitRepository) {
        this.repository = repository;
        this.outfitRepository = outfitRepository;
    }

    public Comment findById(int commentId) {
        return repository.findById(commentId);
    }

    public List<Comment> findByOutfit(int outfitId) {
        return repository.findByOutfit(outfitId);
    }

    public List <Comment> findByHidden(boolean hidden) {
        return repository.findByHidden();
    }

    public Result<Comment> add(Comment comment) {
        Result<Comment> result = validate(comment);
        if (!result.isSuccess()) {
            return result;
        }

        if (comment.getCommentId() != 0) {
            result.addMessage("commentId cannot be set for `add` operation", ResultType.INVALID);
            return result;
        }

        comment = repository.add(comment);
        result.setPayload(comment);
        return result;
    }

    public Result<Comment> update(Comment comment) {
        Result<Comment> result = validate(comment);
        if (!result.isSuccess()) {
            return result;
        }

        if (comment.getCommentId() <= 0) {
            result.addMessage("commentId must be set for `update` operation", ResultType.INVALID);
            return result;
        }

        if (!repository.update(comment)) {
            String msg = String.format("commentId: %s, not found", comment.getCommentId());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }

        return result;
    }

    public boolean deleteById(int commentId) {
        return repository.deleteById(commentId);
    }

    //TODO look into more validations for comments

    private Result<Comment> validate(Comment comment) {
        Result<Comment> result = new Result<>();

        if (comment == null) {
            result.addMessage("comment cannot be null", ResultType.INVALID);
            return result;
        }

        if ((comment.getUserId()) ==0) {
            result.addMessage("User Id is required", ResultType.INVALID);
        }

        if (Validations.isNullOrBlank(comment.getContent())) {
            result.addMessage("content is required", ResultType.INVALID);
        }

        if ((comment.getOutfitId()) ==0) {
            result.addMessage("Outfit Id is required", ResultType.INVALID);
        }


        return result;
    }
}
