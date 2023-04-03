package learn.capstone;

import learn.capstone.domain.Result;
import learn.capstone.domain.ResultType;
import learn.capstone.models.Outfit;
import learn.capstone.models.ClothingItem;

import java.time.LocalDate;
import java.util.List;

public class TestHelper {
    public static <T> Result<T> makeResult(String message, ResultType type) {
        Result<T> result = new Result<>();
        result.addMessage(message, type);
        return result;
    }

    public static <T> Result<T> makeResult(T payload) {
        Result<T> result = new Result<>();
        result.setPayload(payload);
        return result;
    }

    public static Outfit makeOutfit() {

        Outfit outfit = new Outfit();
        outfit.setOutfitId(1);
        outfit.setUserId(1);
        outfit.setShirtId(1);
        outfit.setPantsId(1);
        outfit.setHatId(1);
        outfit.setDateCreated(LocalDate.of(2022, 9, 16));
        outfit.setDuckId(1);
        outfit.setHidden(false);
        outfit.setPosted(true);

//        outfit.setItems(List.of(
//                new ClothingItem(1, "Shirt", "ClothingItemImage #1", false),
//                new ClothingItem(2, "Pants", "ClothingItemImage #2", false)
//        ));

        return outfit;
    }
}
