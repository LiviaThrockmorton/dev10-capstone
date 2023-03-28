package learn.capstone.domain;

import learn.capstone.data.OutfitRepository;
import learn.capstone.data.ClothingItemRepository;
import learn.capstone.models.Outfit;
import learn.capstone.models.ClothingItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static learn.capstone.TestHelper.makeOutfit;
import static learn.capstone.TestHelper.makeResult;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ClothingItemServiceTest {

    @MockBean
    ClothingItemRepository itemRepository;

    @MockBean
    OutfitRepository outfitRepository;

    @Autowired
    ClothingItemService service;

    @Test
    void shouldAdd() {
        Result<ClothingItem> expected = makeResult(new ClothingItem(5, "DuckImage", "ClothingItemImage", true));

        when(itemRepository.add(any())).thenReturn(new ClothingItem(5, "DuckImage", "ClothingItemImage", true));
        when(outfitRepository.findById(anyInt())).thenReturn(makeOutfit());

        ClothingItem arg = new ClothingItem(0, "DuckImage", "ClothingItemImage", true);
        Result<ClothingItem> actual = service.add(arg);

        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddBlankDuckImage() {

        Result<ClothingItem> expected = makeResult("item_type is required", ResultType.INVALID);

        ClothingItem arg = new ClothingItem(0, "\t", "ClothingItemImage", true);
        Result<ClothingItem> actual = service.add(arg);

        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddDuplicateClothingItemImage() {

        Result<ClothingItem> expected = makeResult("duplicate item_type and clothingItemImage", ResultType.INVALID);

        Outfit outfit = makeOutfit();
        when(outfitRepository.findById(anyInt())).thenReturn(outfit);

        ClothingItem arg = new ClothingItem(0, "DuckImage #1", "ClothingItemImage #1", true);
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
    void shouldUpdateExisting() {
        Result<ClothingItem> expected = makeResult(null);
        when(itemRepository.update(any())).thenReturn(true);
        when(outfitRepository.findById(anyInt())).thenReturn(makeOutfit());
        ClothingItem arg = new ClothingItem(3, "DuckImage", "ClothingItemImage", true);
        Result<ClothingItem> actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateMissing() {
        Result<ClothingItem> expected = makeResult("item id 15 not found", ResultType.NOT_FOUND);
        when(itemRepository.update(any())).thenReturn(false);
        when(outfitRepository.findById(anyInt())).thenReturn(makeOutfit());
        ClothingItem arg = new ClothingItem(15, "DuckImage", "ClothingItemImage", true);
        Result<ClothingItem> actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateBlankDuckImage() {
        Result<ClothingItem> expected = makeResult("item_type is required", ResultType.INVALID);
        ClothingItem arg = new ClothingItem(3, " ", "ClothingItemImage", true);
        Result<ClothingItem> actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateDuplicateClothingItemImage() {

        Result<ClothingItem> expected = makeResult("duplicate item_type and outfit", ResultType.INVALID);

        Outfit outfit = makeOutfit();
        when(outfitRepository.findById(anyInt())).thenReturn(outfit);

        ClothingItem arg = new ClothingItem(2, "DuckImage #1", "ClothingItemImage #1", true);
        Result<ClothingItem> actual = service.add(arg);

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
        Result<Void> actual = service.deleteById(5);
    }

    @Test
    void shouldNotDeleteMissing() {
        Result<Void> expected = makeResult("item id 15 not found", ResultType.NOT_FOUND);
        Result<Void> actual = service.deleteById(15);
        assertEquals(expected, actual);
    }

}