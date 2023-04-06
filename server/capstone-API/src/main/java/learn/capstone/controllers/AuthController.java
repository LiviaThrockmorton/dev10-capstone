package learn.capstone.controllers;

import learn.capstone.models.AppUser;
import learn.capstone.models.Duck;
import learn.capstone.security.AppUserResult;
import learn.capstone.security.AppUserService;
import learn.capstone.security.JwtConverter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@ConditionalOnWebApplication
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtConverter converter;
    private final AppUserService appUserService;

    public AuthController(AuthenticationManager authenticationManager, JwtConverter converter, AppUserService appUserService) {
        this.authenticationManager = authenticationManager;
        this.converter = converter;
        this.appUserService = appUserService;
    }
    @GetMapping("/{appUserId}")
    public ResponseEntity<AppUser> loadUserById(@PathVariable int appUserId) {
        AppUser appUser = appUserService.loadUserById(appUserId);
        if (appUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(appUser);
    }


    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody Map<String, String> credentials) {
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(credentials.get("username"), credentials.get("password"));

        try {
            Authentication authentication = authenticationManager.authenticate(authToken);

            if (authentication.isAuthenticated()) {
                String jwtToken = converter.getTokenFromUser((AppUser) authentication.getPrincipal());
                HashMap<String, String> map = new HashMap<>();
                map.put("jwt_token", jwtToken);
                return new ResponseEntity<>(map, HttpStatus.OK);
            }
        } catch (AuthenticationException ex) {
            System.out.println(ex);
        }

        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @PostMapping("refresh_token")
    public ResponseEntity<Map<String, String>> refreshToken(@AuthenticationPrincipal AppUser appUser) {
        String jwtToken = converter.getTokenFromUser(appUser);

        HashMap<String, String> map = new HashMap<>();
        map.put("jwt_token", jwtToken);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PostMapping("/create_account")
    public ResponseEntity<?> createAccount(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");
        AppUserResult result = appUserService.create(username, password);
        if (!result.isSuccess()) {
            return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.BAD_REQUEST);
        }
        HashMap<String, Integer> map = new HashMap<>();
        map.put("appUserId", result.getAppUser().getAppUserId());
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }
}
