//package learn.capstone.domain;
//
//import learn.capstone.data.OutfitRepository;
//import learn.capstone.models.Outfit;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import static learn.capstone.TestHelper.makeOutfit;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
//class OutfitServiceTest {
//
//    @Autowired
//    OutfitService service;
//
//    @MockBean
//    OutfitRepository repository;
//
//    @Test
//    void shouldFindHazel() {
//        // pass-through test, probably not useful
//        Outfit expected = makeOutfit();
//        when(repository.findById(1)).thenReturn(expected);
//        Outfit actual = service.findById(1);
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void shouldNotAddWhenInvalid() {
//        Outfit outfit = makeOutfit();
//        Result<Outfit> result = service.add(outfit);
//        assertEquals(ResultType.INVALID, result.getType());
//
//        outfit.setOutfitId(0);
//        outfit.setShirtId(0);
//        result = service.add(outfit);
//        assertEquals(ResultType.INVALID, result.getType());
//    }
//
//    @Test
//    void shouldNotAddWhenValid() {
//        Outfit expected = makeOutfit();
//        Outfit arg = makeOutfit();
//        arg.setOutfitId(0);
//
//        when(repository.add(arg)).thenReturn(expected);
//        Result<Outfit> result = service.add(arg);
//        assertEquals(ResultType.SUCCESS, result.getType());
//
//        assertEquals(expected, result.getPayload());
//    }
//}