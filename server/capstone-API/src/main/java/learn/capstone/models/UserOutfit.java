package learn.capstone.models;

import java.util.Objects;

public class UserOutfit {

    private int appUserId;


    private int outfitId;

    private Outfit outfit;

    public Outfit getOutfit() {
        return outfit;
    }

    public void setOutfit(Outfit outfit) {
        this.outfit = outfit;
    }

    public int getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(int appUserId) {
        this.appUserId = appUserId;
    }

    public int getOutfitId() {
        return outfitId;
    }

    public void setOutfitId(int outfitId) {
        this.outfitId = outfitId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserOutfit that = (UserOutfit) o;
        return appUserId == that.appUserId && outfitId == that.outfitId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(appUserId, outfitId);
    }
}
