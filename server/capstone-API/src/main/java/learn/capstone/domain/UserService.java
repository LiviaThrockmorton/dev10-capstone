package learn.capstone.domain;


import learn.capstone.data.AppUserRepositoryInterface;
import learn.capstone.models.AppUser;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final AppUserRepositoryInterface appUserRepositoryInterface;


    public UserService(AppUserRepositoryInterface appUserRepositoryInterface) {
        this.appUserRepositoryInterface = appUserRepositoryInterface;

    }

    public List<AppUser> findAll() {
        return appUserRepositoryInterface.findAll();
    }

    public AppUser findById(int appUserId) {
        return appUserRepositoryInterface.findById(appUserId);
    }



    public Result<AppUser> add(AppUser appUser) {
        Result<AppUser> result = validate(appUser);
        if (!result.isSuccess()) {
            return result;
        }

        if (appUser.getAppUserId() != 0) {
            result.addMessage("appUserId cannot be set for `add` operation", ResultType.INVALID);
            return result;
        }

        appUser = appUserRepositoryInterface.add(appUser);
        result.setPayload(appUser);
        return result;
    }

    public Result<AppUser> update(AppUser appUser) {
        Result<AppUser> result = validate(appUser);
        if (!result.isSuccess()) {
            return result;
        }

        if (appUser.getAppUserId() <= 0) {
            result.addMessage("appUserId must be set for `update` operation", ResultType.INVALID);
            return result;
        }

        if (!appUserRepositoryInterface.update(appUser)) {
            String msg = String.format("appUserId: %s, not found", appUser.getAppUserId());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }

        return result;
    }

    public boolean deleteById(int appUserId) {
        return appUserRepositoryInterface.deleteById(appUserId);
    }
//

    private Result<AppUser> validate(AppUser appUser) {
        Result<AppUser> result = new Result<>();
        if (appUser == null) {
            result.addMessage("appUser cannot be null", ResultType.INVALID);
            return result;
        }

        if (Validations.isNullOrBlank(appUser.getUsername())) {
            result.addMessage("username is required", ResultType.INVALID);
        }

        if (Validations.isNullOrBlank(appUser.getEmail())) {
            result.addMessage("email is required", ResultType.INVALID);
        }

        return result;
    }
}
