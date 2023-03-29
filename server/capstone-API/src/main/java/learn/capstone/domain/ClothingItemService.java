package learn.capstone.domain;


import learn.capstone.data.ClothingItemRepository;
import learn.capstone.models.ClothingItem;
import learn.capstone.models.Outfit;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClothingItemService {

    private final ClothingItemRepository itemRepository;

    public ClothingItemService(ClothingItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<ClothingItem> findByType(String itemType) {
        return itemRepository.findByType(itemType);
    }

    public ClothingItem findById(int itemId) {

        return itemRepository.findById(itemId);
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
            result.addMessage("Item id " + item.getItemId() + " not found", ResultType.NOT_FOUND);
        }
        return result;
    }

    public Result<Void> deleteById(int itemId) {
        boolean success = itemRepository.deleteById(itemId);
        Result<Void> result = new Result<>();
        if (!success) {
            result.addMessage("Item id " + itemId + " not found", ResultType.NOT_FOUND);
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
            result.addMessage("Item type is required", ResultType.INVALID);
            return result;
        }

        if (Validations.isNullOrBlank(item.getClothingItemImage())) {
            result.addMessage("Image is required", ResultType.INVALID);
            return result;
        }


        return result;
    }
}
