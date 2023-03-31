//package learn.capstone.security;
//
//import learn.capstone.data.AppUserJdbcTemplateRepository;
//import learn.capstone.models.AppUser;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class AppUserService implements UserDetailsService {
//
//    private final AppUserJdbcTemplateRepository repository;
//
//    public AppUserService(AppUserJdbcTemplateRepository repository) {
//        this.repository = repository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        AppUser user = repository.findByUsername(username);
//
//        if (user == null) {
//            throw new UsernameNotFoundException("Username " + username + " not found.");
//        }
//
//        return user;
//    }
//}
