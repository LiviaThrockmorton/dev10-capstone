package learn.capstone.domain;

import learn.capstone.data.OutfitRepository;
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

    public Result<Outfit> add(Outfit outfit) {
        Result<Outfit> result = validate(outfit);
        if (!result.isSuccess()) {
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

        if (outfit.getDateCreated() != null && outfit.getDateCreated().isAfter(LocalDate.now().minusYears(12))) {
            result.addMessage("outfits younger than 12 are not allowed", ResultType.INVALID);
        }

        if (outfit.getDuckId() < 36 || outfit.getDuckId() > 96) {
            result.addMessage("duckId must be between 36 and 96 inches", ResultType.INVALID);
        }

        return result;
    }
}
