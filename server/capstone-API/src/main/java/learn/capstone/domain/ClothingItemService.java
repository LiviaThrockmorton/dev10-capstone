package learn.capstone.domain;


import learn.capstone.data.ClothingItemRepository;
import learn.capstone.data.OutfitRepository;
import learn.capstone.models.ClothingItem;
import learn.capstone.models.Outfit;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ClothingItemService {

    private final ClothingItemRepository itemRepository;
    private final OutfitRepository outfitRepository;

    public ClothingItemService(ClothingItemRepository itemRepository, OutfitRepository outfitRepository) {
        this.itemRepository = itemRepository;
        this.outfitRepository = outfitRepository;
    }

    public Result<ClothingItem> add(ClothingItem item) {
        Result<ClothingItem> result = validate(item);
        if (!result.isSuccess()) {
            return result;
        }

        item = itemRepository.add(item);
        if (item == null) {
            result.addMessage("could not save item", ResultType.INVALID);
            return result;
        }

        result.setPayload(item);
        return result;
    }

    public Result<ClothingItem> update(ClothingItem item) {
        Result<ClothingItem> result = validate(item);
        if (!result.isSuccess()) {
            return result;
        }
        boolean success = itemRepository.update(item);
        if (!success) {
            result.addMessage("item id " + item.getItemId() + " not found", ResultType.NOT_FOUND);
        }
        return result;
    }

    public Result<Void> deleteById(int itemId) {
        boolean success = itemRepository.deleteById(itemId);
        Result<Void> result = new Result<>();
        if (!success) {
            result.addMessage("item id " + itemId + " not found", ResultType.NOT_FOUND);
        }
        return result;
    }

    private Result<ClothingItem> validate(ClothingItem item) {
        Result<ClothingItem> result = new Result<>();
        if (item == null) {
            result.addMessage("item is null", ResultType.INVALID);
            return result;
        }

        if (Validations.isNullOrBlank(item.getItemType())) {
            result.addMessage("itemType is required", ResultType.INVALID);
            return result;
        }

        Outfit outfit = outfitRepository.findById(item.getItemId());
        for (ClothingItem a : outfit.getItems()) {
            boolean itemTypesMatch = a.getItemType().equalsIgnoreCase(item.getItemType())
                    && a.getItemId() != item.getItemId();
            boolean clothingItemImagesMatch = Objects.equals(a.getClothingItemImage(), item.getClothingItemImage());
            if (itemTypesMatch && clothingItemImagesMatch) {
                result.addMessage("duplicate itemType and outfit", ResultType.INVALID);
            }
        }

        return result;
    }
}
