package learn.capstone.domain;

import learn.capstone.data.CommentRepository;
import learn.capstone.models.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
//@SpringBootTest
class CommentServiceTest {

    @Autowired
    CommentService service;

    @MockBean
    CommentRepository repository;

    //TODO should find by hidden, should find by outfit, should not update date, should not update blank content

    @Test
    void shouldNotAddWhenInvalid() {
        Comment comment = makeComment();
        comment.setUserId(0);

        Result<Comment> actual = service.add(comment);
        assertEquals(ResultType.INVALID, actual.getType());

        comment = makeComment();
        comment.setContent(null);
        actual = service.add(comment);
        assertEquals(ResultType.INVALID, actual.getType());

    }

    @Test
    void shouldAdd() {
        Comment comment = makeComment();
        Comment mockOut = makeComment();
        mockOut.setCommentId(1);

        when(repository.add(comment)).thenReturn(mockOut);

        Result<Comment> actual = service.add(comment);
        assertEquals(ResultType.SUCCESS, actual.getType());
        assertEquals(mockOut, actual.getPayload());
    }

    @Test
    void shouldNotUpdateWhenInvalid() {
        Comment comment = makeComment();
        Result<Comment> actual = service.update(comment);
        assertEquals(ResultType.INVALID, actual.getType());

    }

    @Test
    void shouldUpdate() {
        Comment comment = makeComment();
        comment.setCommentId(1);

        when(repository.update(comment)).thenReturn(true);

        Result<Comment> actual = service.update(comment);
        assertEquals(ResultType.SUCCESS, actual.getType());
    }

    Comment makeComment() {
        Comment comment = new Comment();
        comment.setUserId(1);
        comment.setContent("Test Comment Content");
        comment.setOutfitId(1);
        comment.setUserId(1);
        return comment;
    }
}