package learn.capstone.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Outfit {

    private int outfitId;

    private int duckId;
    private int userId;

    private int shirtId;
    private int pantsId;
    private int hatId;
    private LocalDate dateCreated;

//    private List<ClothingItem> items = new ArrayList<>();
    private boolean posted;

    private boolean hidden;




    public boolean getPosted() {
        return posted;
    }

    public void setPosted(boolean posted) {
        this.posted = posted;
    }

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


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getShirtId() {
        return shirtId;
    }

    public void setShirtId(int shirtId) {
        this.shirtId = shirtId;
    }

    public int getPantsId() {
        return pantsId;
    }

    public void setPantsId(int pantsId) {
        this.pantsId = pantsId;
    }

    public int getHatId() {
        return hatId;
    }

    public void setHatId(int hatId) {
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

//    public List<ClothingItem> getItems() {
//        return items;
//    }
//
//    public void setItems(List<ClothingItem> items) {
//        this.items = items;
//    }
}
