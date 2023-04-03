package learn.capstone.data;

import learn.capstone.models.Outfit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
//@SpringBootTest
class OutfitJdbcTemplateRepositoryTest {

    final static int NEXT_ID = 6;

    @Autowired
    OutfitJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindAll() {
        List<Outfit> outfits = repository.findAll();
        assertNotNull(outfits);


        assertTrue(outfits.size() >= 3 && outfits.size() <= 8);
    }

//    insert into outfit (outfit_id, app_user_id, shirt_id, pants_id, hat_id, date_created, duck_id, posted, hidden) values
//(1, 1, 1, 3, 5, '2023-03-28', 1, true, false),

    @Test
    void shouldFindOutfit1() {
        Outfit outfit = repository.findById(1);
        assertEquals(1, outfit.getOutfitId());
        assertEquals(1, outfit.getUserId());
        assertEquals(1, outfit.getShirtId());
        assertEquals(3, outfit.getPantsId());
        assertEquals(5, outfit.getHatId());
        assertEquals(1, outfit.getDuckId());


    }

    @Test
    void shouldAdd() {
        // all fields
        Outfit outfit = makeOutfit();
        Outfit actual = repository.add(outfit);
        assertNotNull(actual);
        assertEquals(NEXT_ID, actual.getOutfitId());

        // null dateCreated
        outfit = makeOutfit();
        outfit.setDateCreated(null);
        actual = repository.add(outfit);
        assertNotNull(actual);
        assertEquals(NEXT_ID + 1, actual.getOutfitId());
    }

    @Test
    void shouldUpdate() {
        Outfit outfit = makeOutfit();
        outfit.setOutfitId(2);
        assertTrue(repository.update(outfit));
        outfit.setOutfitId(13);
        assertFalse(repository.update(outfit));
    }

    @Test
    void shouldDelete() {
        assertTrue(repository.deleteById(3));
//        assertFalse(repository.deleteById(2));
    }

    private Outfit makeOutfit() {
        Outfit outfit =  new Outfit();

        outfit.setDuckId(1);
        outfit.setUserId(1);
        outfit.setShirtId(1);
        outfit.setPantsId(1);
        outfit.setHatId(1);
        outfit.setDateCreated(LocalDate.of(2022,1,1));
        outfit.setPosted(false);
        outfit.setHidden(false);





        return outfit;

    }
}