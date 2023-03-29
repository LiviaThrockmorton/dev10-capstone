package learn.capstone.data;

import learn.capstone.models.ClothingItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ClothingItemJdbcTemplateRepositoryTest {

    final static int NEXT_ID = 4;

    @Autowired
    ClothingItemJdbcTemplateRepository repository;

//    @Autowired
//    KnownGoodState knownGoodState;
//
//    @BeforeEach
//    void setup() {
//        knownGoodState.set();
//    }

    @Test
    void shouldAdd() {
        ClothingItem arg = new ClothingItem(0, "Test ClothingItem", "Test ClothingItemImage", false);
        ClothingItem expected = new ClothingItem(NEXT_ID, "Test ClothingItem", "Test ClothingItemImage", false);
        ClothingItem actual = repository.add(arg);
        assertEquals(expected, actual);
    }

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