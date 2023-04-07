package learn.capstone.domain;

import learn.capstone.data.OutfitRepository;
import learn.capstone.models.Duck;
import learn.capstone.models.Outfit;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OutfitService {

    private final OutfitRepository repository;

    public OutfitService(OutfitRepository repository) {
        this.repository = repository;
    }

    public List<Outfit> findAll() {
        return repository.findAll();
    }

    public Outfit findById(int outfitId) {

        return repository.findById(outfitId);
    }

    public List<Outfit> findByUser(int userId) {
        return repository.findByUser(userId);
    }

    public Result<Outfit> add(Outfit outfit) {
        Result<Outfit> result = validate(outfit);
        if (!result.isSuccess()) {
            return result;
        }

        if (outfit.getUserId() == 0) {
            result.addMessage("Outfit needs a user", ResultType.INVALID);
            return result;
        }

        if (outfit.getOutfitId() != 0) {
            result.addMessage("outfitId cannot be set for `add` operation", ResultType.INVALID);
            return result;
        }

        outfit = repository.add(outfit);
        result.setPayload(outfit);
        return result;
    }

    public Result<Outfit> update(Outfit outfit) {
        Result<Outfit> result = validate(outfit);
        if (!result.isSuccess()) {
            return result;
        }

        if (outfit.getOutfitId() <= 0) {
            result.addMessage("outfitId must be set for `update` operation", ResultType.INVALID);
            return result;
        }

        if (!repository.update(outfit)) {
            String msg = String.format("outfitId: %s, not found", outfit.getOutfitId());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }

        return result;
    }

    public boolean deleteById(int outfitId) {
        return repository.deleteById(outfitId);
    }

    private Result<Outfit> validate(Outfit outfit) {
        Result<Outfit> result = new Result<>();
        if (outfit == null) {
            result.addMessage("outfit cannot be null", ResultType.INVALID);
            return result;
        }

//        if (Validations.isNullOrBlank(outfit.getShirtId())) {
//            result.addMessage("shirtId is required", ResultType.INVALID);
//        }
//
//        if (Validations.isNullOrBlank(outfit.getHatId())) {
//            result.addMessage("hatId is required", ResultType.INVALID);
//        }

        if(outfit.getShirtId() == 0 && outfit.getPantsId()==0&&outfit.getHatId() ==0){
            result.addMessage("No clothing items found.", ResultType.INVALID);
        }

        if (outfit.getDateCreated() != null && outfit.getDateCreated().isAfter(LocalDate.now())) {
            result.addMessage("Date cannot be set to in the future.", ResultType.INVALID);
        }

        if (outfit.getOutfitId() < 0 || outfit.getOutfitId() > 100) {
            result.addMessage("Outfit Id must be between 0 and 100", ResultType.INVALID);
        }

        return result;
    }
}
