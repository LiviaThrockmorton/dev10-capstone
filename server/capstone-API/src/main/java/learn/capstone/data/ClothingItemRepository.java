package learn.capstone.data;

import learn.capstone.models.ClothingItem;

public interface ClothingItemRepository {
    ClothingItem add(ClothingItem item);

    boolean update(ClothingItem item);

    boolean deleteById(int itemId);
}
