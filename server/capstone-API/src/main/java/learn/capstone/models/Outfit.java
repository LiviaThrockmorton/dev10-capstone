package learn.capstone.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Outfit {

    private int outfitId;
    private String shirtId;
    private String pantsId;
    private String hatId;
    private LocalDate dateCreated;
    private int duckId;
    private List<ClothingItem> items = new ArrayList<>();

    private boolean hidden;

    public boolean getHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }


    public int getOutfitId() {
        return outfitId;
    }

    public void setOutfitId(int outfitId) {
        this.outfitId = outfitId;
    }

    public String getShirtId() {
        return shirtId;
    }

    public void setShirtId(String shirtId) {
        this.shirtId = shirtId;
    }

    public String getPantsId() {
        return pantsId;
    }

    public void setPantsId(String pantsId) {
        this.pantsId = pantsId;
    }

    public String getHatId() {
        return hatId;
    }

    public void setHatId(String hatId) {
        this.hatId = hatId;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public int getDuckId() {
        return duckId;
    }

    public void setDuckId(int duckId) {
        this.duckId = duckId;
    }

    public List<ClothingItem> getItems() {
        return items;
    }

    public void setItems(List<ClothingItem> items) {
        this.items = items;
    }
}
