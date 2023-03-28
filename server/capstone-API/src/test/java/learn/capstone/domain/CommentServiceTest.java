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
class CommentServiceTest {

    @Autowired
    CommentService service;

    @MockBean
    CommentRepository repository;

    @Test
    void shouldNotAddWhenInvalid() {
        Comment comment = makeComment();
        comment.setUserId("   ");

        Result<Comment> actual = service.add(comment);
        assertEquals(ResultType.INVALID, actual.getType());

        comment = makeComment();
        comment.setContent(null);
        actual = service.add(comment);
        assertEquals(ResultType.INVALID, actual.getType());

        comment = makeComment();
        comment.setOutfitId("\t");
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

        comment = makeComment();
        comment.setCommentId(1);
        comment.setCountryCode("");
        actual = service.update(comment);
        assertEquals(ResultType.INVALID, actual.getType());

        comment = makeComment();
        comment.setCommentId(1);
        comment.setPostalCode(null);
        actual = service.update(comment);
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
        comment.setUserId("HQ");
        comment.setContent("123 Oak St.");
        comment.setOutfitId("Test OutfitId");
        comment.setRegion("Test Region");
        comment.setCountryCode("TEST");
        comment.setPostalCode("55555-PST");
        comment.setAppUserId(1);
        return comment;
    }
}