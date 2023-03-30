//package learn.capstone.data;
//
//import learn.capstone.models.Outfit;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.time.LocalDate;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
//class OutfitJdbcTemplateRepositoryTest {
//
//    final static int NEXT_ID = 9;
//
//    @Autowired
//    OutfitJdbcTemplateRepository repository;
//
////    @Autowired
////    KnownGoodState knownGoodState;
//
////    @BeforeEach
////    void setup() {
////        knownGoodState.set();
////    }
//
//    @Test
//    void shouldFindAll() {
//        List<Outfit> outfits = repository.findAll();
//        assertNotNull(outfits);
//
//        // can't predict order
//        // if delete is first, we're down to 7
//        // if add is first, we may go as high as 10
//        assertTrue(outfits.size() >= 7 && outfits.size() <= 10);
//    }
//
//    @Test
//    void shouldFindHazel() {
//        Outfit hazel = repository.findById(1);
//        assertEquals(1, hazel.getOutfitId());
//        assertEquals(1, hazel.getShirtId());
//        assertEquals(2, hazel.getItems().size());
//    }
//
//    @Test
//    void shouldAdd() {
//        // all fields
//        Outfit outfit = makeOutfit();
//        Outfit actual = repository.add(outfit);
//        assertNotNull(actual);
//        assertEquals(NEXT_ID, actual.getOutfitId());
//
//        // null dateCreated
//        outfit = makeOutfit();
//        outfit.setDateCreated(null);
//        actual = repository.add(outfit);
//        assertNotNull(actual);
//        assertEquals(NEXT_ID + 1, actual.getOutfitId());
//    }
//
//    @Test
//    void shouldUpdate() {
//        Outfit outfit = makeOutfit();
//        outfit.setOutfitId(3);
//        assertTrue(repository.update(outfit));
//        outfit.setOutfitId(13);
//        assertFalse(repository.update(outfit));
//    }
//
//    @Test
//    void shouldDelete() {
//        assertTrue(repository.deleteById(2));
//        assertFalse(repository.deleteById(2));
//    }
//
//    private Outfit makeOutfit() {
//        Outfit outfit = new Outfit();
//        outfit.setShirtId(1);
//        outfit.setHatId(1);
//        outfit.setDateCreated(LocalDate.of(1985, 8, 15));
//        outfit.setDuckId(66);
//        return outfit;
//    }
//}