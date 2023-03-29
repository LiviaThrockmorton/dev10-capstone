//package learn.capstone.domain;
//
//import learn.capstone.data.UserOutfitRepository;
//import learn.capstone.data.DuckRepository;
//import learn.capstone.models.*;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static learn.capstone.TestHelper.*;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.anyInt;
//import static org.mockito.Mockito.when;
//
//import learn.capstone.data.OutfitRepository;
//import learn.capstone.data.ClothingItemRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
//class DuckServiceTest {
//
//    @MockBean
//    DuckRepository repository;
//
//    @Autowired
//    DuckService service;
//
//
//
//    @Test
//    void findAll() {
//
//    }
//
//
//    @Test
//    void findById() {
//        Duck expected = makeDuck();
//        when(repository.findById(3)).thenReturn(makeDuck());
//    }
//
//    @Test
//    void shouldNotFindNullId() {
//
//        when(repository.findById(789)).thenReturn(null);
//        assertNull(service.findById(789));
//    }
//
//
//    @Test
//    void add() {
//        Duck clearance = new Duck(0, "DuckImage");
//        Result<Duck> expected = makeResult(makeDuck());
//        when(repository.add(clearance)).thenReturn(makeDuck());
//
//        Duck arg = new Duck(0,"DuckImage");
//        Result<Duck> actual = service.add(arg);
//
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void shouldNotAddBlankDuckImage() {
//
//        Result<Duck> expected = makeResult("Security Clearance duckImage is required", ResultType.INVALID);
//
//        Duck arg = new Duck(0, "\t");
//        Result<Duck> actual = service.add(arg);
//
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void shouldNotAddDuplicateDuckImage() {
//
//        Result<Duck> expected = makeResult("This security clearance duckImage already exists. To make changes, please update the existing security clearance.", ResultType.INVALID);
//
//
//        List<Duck> ducks = new ArrayList<>();
//        ducks.add(makeDuck());
//
//        Duck arg = new Duck(4, "DuckImage");
//        when(repository.findAll()).thenReturn(ducks);
//
//        Result<Duck> actual = service.add(arg);
//
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void shouldUpdate() {
//        Duck duck = new Duck(1, "TEST");
//
//        when(repository.update(duck)).thenReturn(true);
//        Result<Duck> actual = service.update(duck);
//        assertEquals(ResultType.SUCCESS, actual.getType());
//    }
//
//    @Test
//    void shouldNotUpdateMissing() {
//        Duck duck = new Duck(35, "TEST");
//
//        when(repository.update(duck)).thenReturn(false);
//        Result<Duck> actual = service.update(duck);
//        assertEquals(ResultType.NOT_FOUND, actual.getType());
//    }
//
//    @Test
//    void shouldNotUpdateWhenInvalid() {
//        Duck duck = new Duck(35, null);
//
//        Result<Duck> actual = service.update(duck);
//        assertEquals(ResultType.INVALID, actual.getType());
//
//        duck.setDuckImage("");
//        actual = service.update(duck);
//        assertEquals(ResultType.INVALID, actual.getType());
//
//        duck.setDuckId(0);
//        actual = service.update(duck);
//        assertEquals(ResultType.INVALID, actual.getType());
//    }
//
//    @Test
//    void shouldNotDeleteInUse() {
//        when(repository.getUsageCount(1)).thenReturn(12);
//        Result<Duck> actual = service.deleteById(1);
//        assertEquals(ResultType.INVALID, actual.getType());
//    }
//
//
//    @Test
//    void shouldDeleteExisting() {
//        Result<Duck> expected = makeResult(null);
//        when(repository.deleteById(anyInt())).thenReturn(true);
//        Result<Duck> actual = service.deleteById(1);
//    }
//
//    @Test
//    void shouldNotDeleteMissing() {
//        when (repository.deleteById(15)).thenReturn(false);
//        Result<Duck> expected = makeResult("Security ClearanceId: 15, not found", ResultType.NOT_FOUND);
//        Result<Duck> actual = service.deleteById(15);
//        assertEquals(expected, actual);
//    }
//
//
//
//    public Duck makeDuck(){
//        return new Duck(3, "DuckImage");
//    }
//
//}