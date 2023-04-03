package learn.capstone.data;

import learn.capstone.models.Duck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@SpringBootTest
class DuckJdbcTemplateRepositoryTest {


    final static int NEXT_ID = 6;

    @Autowired
    DuckJdbcTemplateRepository repository;


    @Autowired
    private JdbcTemplate jdbcTemplate;

//    @Autowired
//    KnownGoodState knownGoodState;
//
//    @BeforeEach
//    void setup() {
//        knownGoodState.set();
//    }


    static boolean hasSetup = false;

    @BeforeEach
    void setup() {
        if (!hasSetup) {
            hasSetup = true;
            jdbcTemplate.update("call set_known_good_state();");
        }
    }



    @Test
    void shouldFindAll() {
        List<Duck> ducks = repository.findAll();
        assertNotNull(ducks);


        assertTrue(ducks.size() >= 3 && ducks.size() <= 7);
    }

    @Test
    void shouldFindById() {
        Duck duck1 = new Duck(1, "/svgs/yellow-duck.svg", false);


        Duck actual = repository.findById(1);
        assertEquals(duck1, actual);

        actual = repository.findById(2);


        actual = repository.findById(12);
        assertEquals(null, actual);
    }

    @Test
    void shouldNotFindHidden(){

        Duck actual = repository.findById(4);
        assertNull(actual);
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
//        assertFalse(repository.deleteById(2));
    }

//TODO need to implement this once outfit is complete
    @Test
    void shouldGetUsageCount(){
        assertTrue(repository.getUsageCount(1) > 0);
//        assertFalse(repository.getUsageCount(2) > 0);
    }


    public Duck makeDuck(){
        Duck duck = new Duck(6, "[INSERT URL HERE]", false);
        return duck;
    }



}
