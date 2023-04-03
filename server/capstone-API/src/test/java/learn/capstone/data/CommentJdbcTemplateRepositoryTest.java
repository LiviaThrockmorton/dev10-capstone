package learn.capstone.data;

import learn.capstone.models.Comment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class CommentJdbcTemplateRepositoryTest {

    final static int NEXT_LOCATION_ID = 7;

    @Autowired
    CommentJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindHQ() {
        Comment actual = repository.findById(1);
        assertNotNull(actual);
        assertEquals(1, actual.getCommentId());
        assertEquals("HQ", actual.getUserId());
    }

    @Test
    void shouldAdd() {
        Comment comment = makeComment();
        Comment actual = repository.add(comment);
        assertNotNull(actual);
        assertEquals(NEXT_LOCATION_ID, actual.getCommentId());
    }

    @Test
    void shouldUpdate() {
        Comment comment = makeComment();
        comment.setCommentId(6);
        assertTrue(repository.update(comment));
        comment.setCommentId(16);
        assertFalse(repository.update(comment));
    }

    @Test
    void shouldDelete() {
        assertTrue(repository.deleteById(5));
        assertFalse(repository.deleteById(5));
    }

    Comment makeComment() {
        Comment comment = new Comment();
        comment.setUserId(1);
        comment.setContent("Test Comment");
        comment.setOutfitId(1);
        return comment;
    }
}