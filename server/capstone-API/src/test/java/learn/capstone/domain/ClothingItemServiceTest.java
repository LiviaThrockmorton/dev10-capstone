package learn.capstone.domain;


import learn.capstone.data.ClothingItemRepository;
import learn.capstone.models.Outfit;
import learn.capstone.models.ClothingItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static learn.capstone.TestHelper.makeResult;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@SpringBootTest
class ClothingItemServiceTest {

    @MockBean
    ClothingItemRepository itemRepository;


    @Autowired
    ClothingItemService service;

    @Test
    void shouldAdd() {
        Result<ClothingItem> expected = makeResult(new ClothingItem(7, "Shirt", "ClothingItemImage", true));

        when(itemRepository.add(any())).thenReturn(new ClothingItem(7, "Shirt", "ClothingItemImage", true));


        ClothingItem arg = new ClothingItem(0, "Shirt", "ClothingItemImage", true);
        Result<ClothingItem> actual = service.add(arg);

        assertEquals(expected, actual);

    }

    @Test
    void shouldNotAddBlankImage() {

        Result<ClothingItem> expected = makeResult("Image is required", ResultType.INVALID);

        ClothingItem arg = new ClothingItem(0, "hat", " ", true);
        Result<ClothingItem> actual = service.add(arg);

        assertEquals(expected, actual);
    }
    @Test
    void shouldNotAddBlankType() {

        Result<ClothingItem> expected = makeResult("Item type is required", ResultType.INVALID);

        ClothingItem arg = new ClothingItem(0, "        ", "Test Image", true);
        Result<ClothingItem> actual = service.add(arg);

        assertEquals(expected, actual);
    }


    @Test
    void shouldNotAddNull() {
        Result<ClothingItem> expected = makeResult("item is null", ResultType.INVALID);
        Result<ClothingItem> actual = service.add(null);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddInvalidClothingItem() {

        Result<ClothingItem> expected = makeResult("Invalid item, only hat, pants, and shirt are allowed.", ResultType.INVALID);


        ClothingItem arg = new ClothingItem(2, "test item", "ClothingItemImage #1", false);
        Result<ClothingItem> actual = service.add(arg);

        assertEquals(expected, actual);
    }

    @Test
    void shouldUpdateExisting() {
        Result<ClothingItem> expected = makeResult(null);
        when(itemRepository.update(any())).thenReturn(true);
        ClothingItem arg = new ClothingItem(2, "Hat", "ClothingItemImage", true);
        Result<ClothingItem> actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateMissing() {
        Result<ClothingItem> expected = makeResult("Item id 14 not found", ResultType.NOT_FOUND);
        when(itemRepository.update(any())).thenReturn(false);

        ClothingItem arg = new ClothingItem(14, "Hat", "[image goes here]", true);
        Result<ClothingItem> actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateBlankImage() {
        Result<ClothingItem> expected = makeResult("Image is required", ResultType.INVALID);
        ClothingItem arg = new ClothingItem(3, "Hat", "", true);
        Result<ClothingItem> actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateBlankType() {
        Result<ClothingItem> expected = makeResult("Item type is required", ResultType.INVALID);
        ClothingItem arg = new ClothingItem(3, " ", "Test Image", true);
        Result<ClothingItem> actual = service.update(arg);
        assertEquals(expected, actual);
    }



    @Test
    void shouldNotUpdateNull() {
        Result<ClothingItem> expected = makeResult("item is null", ResultType.INVALID);
        Result<ClothingItem> actual = service.update(null);
        assertEquals(expected, actual);
    }

    @Test
    void shouldDeleteExisting() {
        Result<Void> expected = makeResult(null);
        when(itemRepository.deleteById(anyInt())).thenReturn(true);
        Result<ClothingItem> actual = service.deleteById(5);
        assertEquals(expected, actual);
    }

//    @Test
//    void shouldNotDeleteMissing() {
//        Result<ClothingItem> expected = makeResult("Item id 15 not found", ResultType.NOT_FOUND);
//        Result<ClothingItem> actual = service.deleteById(15);
//        assertEquals(expected, actual);
//    }

}