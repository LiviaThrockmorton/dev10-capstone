package learn.capstone.models;

import java.time.LocalDateTime;
import java.util.Objects;

public class Comment {

    private int commentId;
    private int userId;
    private String content;
    private int outfitId;
    private LocalDateTime dateTime;
    private boolean hidden;


    public boolean getHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getOutfitId() {
        return outfitId;
    }

    public void setOutfitId(int outfitId) {
        this.outfitId = outfitId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return commentId == comment.commentId && userId == comment.userId && content == comment.content && outfitId == comment.outfitId && hidden == comment.hidden && Objects.equals(dateTime, comment.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId, userId, content, outfitId, dateTime, hidden);
    }
}