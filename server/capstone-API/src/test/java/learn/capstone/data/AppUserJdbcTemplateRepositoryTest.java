package learn.capstone.data;

import learn.capstone.models.AppUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class AppUserJdbcTemplateRepositoryTest {

    @Autowired
    AppUserJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }
//
////    @Test
////    void shouldFindAppUsers() {
////        List<AppUser> agencies = repository.findByType();
////        assertNotNull(agencies);
////        assertTrue(agencies.size() > 0);
////    }
//
////    @Test
////    void shouldFindAcme() {
////        AppUser acme = repository.findById(1);
////        assertEquals("ACME", acme.getUsername());
////    }
//
//    @Test
//    void shouldAddAppUser() {
//        AppUser appUser = new AppUser();
//        appUser.setUsername("TEST");
//        appUser.setEmail("Test AppUser");
//        appUser.setHidden(false);
//        appUser.setPassword("test");
//        AppUser actual = repository.add(appUser);
//        assertNotNull(actual);
//        assertEquals(3, actual.getAppUserId());
//    }
//
//    @Test
//    void shouldUpdateAppUser() {
//
//        AppUser appUser = new AppUser();
//        appUser.setAppUserId(3);
//        appUser.setUsername("TEST");
//        appUser.setEmail("Test AppUser");
//        appUser.setHidden(false);
//        appUser.setPassword("test");
//
//        assertTrue(repository.update(appUser));
//    }

//    @Test
//    void shouldDeleteAppUser() {
//        assertTrue(repository.deleteById(2));
////        assertFalse(repository.deleteById(2));
//    }
}