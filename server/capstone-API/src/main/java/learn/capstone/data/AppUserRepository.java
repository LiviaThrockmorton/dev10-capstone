//package learn.capstone.data;
//
//import learn.capstone.models.AppUser;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//public interface AppUserRepository {
//    List<AppUser> findAll();
//
//    @Transactional
//    AppUser findById(int appUserId);
//
//    AppUser add(AppUser appUser);
//
//    boolean update(AppUser appUser);
//
//    @Transactional
//    boolean deleteById(int appUserId);
//}
