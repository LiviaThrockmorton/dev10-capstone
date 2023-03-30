package learn.capstone.domain;

import learn.capstone.data.DuckRepository;
import learn.capstone.models.Duck;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DuckService {



    private final DuckRepository repository;

    public DuckService(DuckRepository repository) {
        this.repository = repository;
    }

    public List<Duck> findAll() {
        return repository.findAll();
    }

    public Duck findById(int duckId) {
        return repository.findById(duckId);
    }

    public Result<Duck> add(Duck duck) {
        Result<Duck> result = validate(duck);
        if (!result.isSuccess()) {
            return result;
        }

        if (duck.getDuckId() != 0) {
            result.addMessage("Id cannot be set for `add` operation", ResultType.INVALID);
            return result;
        }

        duck = repository.add(duck);
        result.setPayload(duck);
        return result;
    }

    public Result<Duck> update(Duck duck) {
        Result<Duck> result = validate(duck);
        if (!result.isSuccess()) {
            return result;
        }

        if (duck.getDuckId() <= 0) {
            result.addMessage("Duck Id must be set for `update` operation", ResultType.INVALID);
            return result;
        }

        if (!repository.update(duck)) {
            String msg = String.format("Duck Id: %s, not found", duck.getDuckId());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }

        return result;
    }

    public Result<Duck> deleteById(int duckId) {
        Result<Duck> result = numberOfUsages(duckId);
        if (!result.isSuccess()) {
            return result;
        }

        if (!repository.deleteById(duckId)) {
            String msg = String.format("Duck Id: %s, not found", duckId);
            result.addMessage(msg, ResultType.NOT_FOUND);
        }

        return result;
    }


    //validate delete
    //int getUsageCount(int duckId)
    //if > 0 = problem

    private Result<Duck> numberOfUsages(int duckId) {
        Result<Duck> result = new Result<>();
        int count = repository.getUsageCount(duckId);
        if(count >0){
            result.addMessage("This Duck is in use in " + count + " outfits. It cannot be deleted at this time.", ResultType.INVALID);
        }
        return result;
    }




    private Result<Duck> validate(Duck duck) {
        Result<Duck> result = new Result<>();
        if (duck == null) {
            result.addMessage("Duck cannot be null", ResultType.INVALID);
            return result;
        }

        if (Validations.isNullOrBlank(duck.getDuckImage())) {
            result.addMessage("Image is required", ResultType.INVALID);
        }

        return result;

    }



}
