package learn.capstone.models;

import java.util.Objects;

public class ClothingItem {
    private int itemId;
    private String itemType;
    private String clothingItemImage;
    private boolean hidden;

    public ClothingItem() {
    }

    public boolean getHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public ClothingItem(int itemId, String itemType, String clothingItemImage, boolean hidden) {
        this.itemId = itemId;
        this.itemType = itemType;
        this.clothingItemImage = clothingItemImage;
        this.hidden = hidden;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getClothingItemImage() {
        return clothingItemImage;
    }

    public void setClothingItemImage(String clothingItemImage) {
        this.clothingItemImage = clothingItemImage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClothingItem that = (ClothingItem) o;
        return itemId == that.itemId && hidden == that.hidden && Objects.equals(itemType, that.itemType) && Objects.equals(clothingItemImage, that.clothingItemImage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, itemType, clothingItemImage, hidden);
    }
}
