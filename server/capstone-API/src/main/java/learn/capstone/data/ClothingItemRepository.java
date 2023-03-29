package learn.capstone.data;

import learn.capstone.models.ClothingItem;
import learn.capstone.models.Outfit;

import java.util.List;

public interface ClothingItemRepository {

    List<ClothingItem> findByType(String itemType);

    ClothingItem findById(int itemId);



    ClothingItem add(ClothingItem item);

    boolean update(ClothingItem item);

    boolean deleteById(int itemId);
}
