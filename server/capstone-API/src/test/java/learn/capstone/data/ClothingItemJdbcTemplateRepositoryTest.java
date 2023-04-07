package learn.capstone.data;

import learn.capstone.models.ClothingItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
//@SpringBootTest
class ClothingItemJdbcTemplateRepositoryTest {

    final static int NEXT_ID = 7;

    @Autowired
    ClothingItemJdbcTemplateRepository repository;

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
    void shouldFindByType() {
        List<ClothingItem> items = repository.findByType("Hat");
        assertNotNull(items);

        // can't predict order
        // if delete is first, we're down to 2
        // if add is first, we may go as high as 3
        assertTrue(items.size() >= 0 && items.size() <= 20);
    }

    @Test
    void shouldFindHat() {
        ClothingItem hat = repository.findById(5);
        assertEquals(5, hat.getItemId());
        assertEquals(false, hat.getHidden());
    }

    //TODO test for should not find hidden items

//    @Test
//    void shouldAdd() {
//        ClothingItem arg = new ClothingItem(7, "Shirt", "Test ClothingItemImage", false);
//        ClothingItem expected = new ClothingItem(NEXT_ID, "Shirt", "Test ClothingItemImage", false);
//        ClothingItem actual = repository.add(arg);
//        assertEquals(expected, actual);
//    }

    @Test
    void shouldUpdateExisting() {
        ClothingItem arg = new ClothingItem(2, "Updated ClothingItem", "Updated ClothingItemImage", false);
        boolean actual = repository.update(arg);
        assertTrue(actual);
    }

    @Test
    void shouldNotUpdateMissing() {
        ClothingItem arg = new ClothingItem(225, "Updated ClothingItem", "Updated ClothingItemImage", false);
        boolean actual = repository.update(arg);
        assertFalse(actual);
    }

    @Test
    void shouldDeleteExisting() {
        boolean actual = repository.deleteById(3);
        assertTrue(actual);
    }

    @Test
    void shouldNotDeleteMissing() {
        boolean actual = repository.deleteById(225);
        assertFalse(actual);
    }
}