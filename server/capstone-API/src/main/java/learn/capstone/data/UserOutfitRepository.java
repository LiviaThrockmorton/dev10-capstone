package learn.capstone.data;

import learn.capstone.models.UserOutfit;

public interface UserOutfitRepository {
    boolean add(UserOutfit appUserOutfit);

    boolean update(UserOutfit appUserOutfit);

    boolean deleteByKey(int appUserId, int outfitId);
}
