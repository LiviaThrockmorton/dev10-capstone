package learn.capstone.domain;

import learn.capstone.data.AppUserRepository;
import learn.capstone.models.AppUser;
import learn.capstone.security.AppUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class AppUserServiceTest {

    @Autowired
    AppUserService service;

    @MockBean
    AppUserRepository appUserRepository;

//    @Test
//    void shouldAdd() {
//        AppUser appUser = new AppUser(0, "TEST", "Long DuckImage Test");
//        AppUser mockOut = new AppUser(5, "TEST", "Long DuckImage Test");
//
//        when(appUserRepository.add(appUser)).thenReturn(mockOut);
//
//        Result<AppUser> actual = service.add(appUser);
//        assertEquals(ResultType.SUCCESS, actual.getType());
//        assertEquals(mockOut, actual.getPayload());
//    }
//
//    @Test
//    void shouldNotAddWhenInvalid() {
//
//        AppUser appUser = new AppUser(35, "TEST", "Long DuckImage Test");
//
//        Result<AppUser> actual = service.add(appUser);
//        assertEquals(ResultType.INVALID, actual.getType());
//
//        appUser.setAppUserId(0);
//        appUser.setUsername(null);
//        actual = service.add(appUser);
//        assertEquals(ResultType.INVALID, actual.getType());
//
//        appUser.setUsername("TEST");
//        appUser.setEmail("   ");
//        actual = service.add(appUser);
//        assertEquals(ResultType.INVALID, actual.getType());
//    }
//
//    @Test
//    void shouldUpdate() {
//        AppUser appUser = new AppUser(5, "TEST", "Long DuckImage Test");
//
//        when(appUserRepository.update(appUser)).thenReturn(true);
//        Result<AppUser> actual = service.update(appUser);
//        assertEquals(ResultType.SUCCESS, actual.getType());
//    }
//
//    @Test
//    void shouldNotUpdateMissing() {
//        AppUser appUser = new AppUser(35, "TEST", "Long DuckImage Test");
//
//        when(appUserRepository.update(appUser)).thenReturn(false);
//        Result<AppUser> actual = service.update(appUser);
//        assertEquals(ResultType.NOT_FOUND, actual.getType());
//    }
//
//    @Test
//    void shouldNotUpdateWhenInvalid() {
//        AppUser appUser = new AppUser(35, null, "Long DuckImage Test");
//
//        Result<AppUser> actual = service.update(appUser);
//        assertEquals(ResultType.INVALID, actual.getType());
//
//        appUser.setUsername("TEST");
//        appUser.setEmail(" ");
//        actual = service.update(appUser);
//        assertEquals(ResultType.INVALID, actual.getType());
//
//        appUser.setAppUserId(0);
//        appUser.setEmail("Long DuckImage Test");
//        actual = service.update(appUser);
//        assertEquals(ResultType.INVALID, actual.getType());
//    }

}