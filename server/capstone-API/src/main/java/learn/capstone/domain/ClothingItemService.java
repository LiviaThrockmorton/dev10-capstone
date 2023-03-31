package learn.capstone.domain;


import learn.capstone.data.ClothingItemRepository;
import learn.capstone.models.ClothingItem;
import learn.capstone.models.Duck;
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

    public Result<ClothingItem> deleteById(int itemId) {
        Result<ClothingItem> result = numberOfUsages(itemId);
        if (!result.isSuccess()) {
            return result;
        }

        if (!itemRepository.deleteById(itemId)) {
            String msg = String.format("Duck Id: %s, not found", itemId);
            result.addMessage(msg, ResultType.NOT_FOUND);
        }

        return result;
    }

    private Result<ClothingItem> numberOfUsages(int itemId) {
        Result<ClothingItem> result = new Result<>();
        int count = itemRepository.getUsageCount(itemId);
        if(count >0){
            result.addMessage("This item is in use in " + count + " outfits. It cannot be deleted at this time.", ResultType.INVALID);
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
        if (!(item.getItemType().equalsIgnoreCase("hat") || item.getItemType().equalsIgnoreCase("pants") ||item.getItemType().equalsIgnoreCase("shirt"))){
            result.addMessage("Invalid item, only hat, pants, and shirt are allowed.", ResultType.INVALID);
            return result;
        }



        return result;
    }




}
