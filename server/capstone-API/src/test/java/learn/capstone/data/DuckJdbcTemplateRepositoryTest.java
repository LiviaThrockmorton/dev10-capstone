package learn.capstone.data;

import learn.capstone.models.Duck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class DuckJdbcTemplateRepositoryTest {


    final static int NEXT_ID = 3;

    @Autowired
    DuckJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }


    @Test
    void shouldFindAll() {
        List<Duck> ducks = repository.findAll();
        assertNotNull(ducks);


        assertTrue(ducks.size() >= 1 && ducks.size() <= 4);
    }

    @Test
    void shouldFindById() {
        Duck secret = new Duck(1, "Secret");
        Duck topSecret = new Duck(2, "Top Secret");

        Duck actual = repository.findById(1);
        assertEquals(secret, actual);

        actual = repository.findById(2);
        assertEquals(topSecret, actual);

        actual = repository.findById(4);
        assertEquals(null, actual);
    }

    @Test
    void shouldAdd() {
        // all fields
        Duck duck = makeDuck();
        Duck actual = repository.add(duck);
        assertNotNull(actual);
        assertEquals(NEXT_ID, actual.getDuckId());
    }

    @Test
    void shouldUpdate() {
        Duck duck = makeDuck();
        assertTrue(repository.update(duck));
        duck.setDuckId(13);
        assertFalse(repository.update(duck));
    }


    @Test
    void shouldDelete() {
        assertTrue(repository.deleteById(2));
        assertFalse(repository.deleteById(2));
    }


    @Test
    void shouldGetUsageCount(){
        assertTrue(repository.getUsageCount(1) > 0);
        assertFalse(repository.getUsageCount(2) > 0);
    }


    public Duck makeDuck(){
        Duck duck = new Duck(3, "Test Clearance");
        return duck;
    }



}
