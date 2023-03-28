package learn.capstone.domain;

import learn.capstone.data.UserOutfitRepository;
import learn.capstone.data.AppUserRepository;
import learn.capstone.models.AppUser;
import learn.capstone.models.UserOutfit;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final UserOutfitRepository appUserOutfitRepository;

    public AppUserService(AppUserRepository appUserRepository, UserOutfitRepository appUserOutfitRepository) {
        this.appUserRepository = appUserRepository;
        this.appUserOutfitRepository = appUserOutfitRepository;
    }

    public List<AppUser> findAll() {
        return appUserRepository.findAll();
    }

    public AppUser findById(int appUserId) {
        return appUserRepository.findById(appUserId);
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

        appUser = appUserRepository.add(appUser);
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

        if (!appUserRepository.update(appUser)) {
            String msg = String.format("appUserId: %s, not found", appUser.getAppUserId());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }

        return result;
    }

    public boolean deleteById(int appUserId) {
        return appUserRepository.deleteById(appUserId);
    }

    public Result<Void> addOutfit(UserOutfit appUserOutfit) {
        Result<Void> result = validate(appUserOutfit);
        if (!result.isSuccess()) {
            return result;
        }

        if (!appUserOutfitRepository.add(appUserOutfit)) {
            result.addMessage("outfit not added", ResultType.INVALID);
        }

        return result;
    }

    public Result<Void> updateOutfit(UserOutfit appUserOutfit) {
        Result<Void> result = validate(appUserOutfit);
        if (!result.isSuccess()) {
            return result;
        }

        if (!appUserOutfitRepository.update(appUserOutfit)) {
            String msg = String.format("update failed for appUser id %s, outfit id %s: not found",
                    appUserOutfit.getAppUserId(),
                    appUserOutfit.getOutfit().getOutfitId());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }

        return result;
    }

    public boolean deleteOutfitByKey(int appUserId, int outfitId) {
        return appUserOutfitRepository.deleteByKey(appUserId, outfitId);
    }

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

    private Result<Void> validate(UserOutfit appUserOutfit) {
        Result<Void> result = new Result<>();
        if (appUserOutfit == null) {
            result.addMessage("appUserOutfit cannot be null", ResultType.INVALID);
            return result;
        }

        if (appUserOutfit.getOutfit() == null) {
            result.addMessage("outfit cannot be null", ResultType.INVALID);
        }

        return result;
    }
}
