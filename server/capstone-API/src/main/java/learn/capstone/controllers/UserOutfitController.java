//package learn.capstone.controllers;
//
//import learn.capstone.domain.UserService;
//import learn.capstone.domain.Result;
//import learn.capstone.domain.UserService;
//import learn.capstone.models.UserOutfit;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@CrossOrigin(origins = {"http://localhost:3000"})
//@RequestMapping("/api/appUser/outfit")
//public class UserOutfitController {
//
//    private final UserService service;
//
//    public UserOutfitController(UserService service) {
//        this.service = service;
//    }
//
//    @PostMapping
//    public ResponseEntity<Object> add(@RequestBody UserOutfit appUserOutfit) {
//        Result<Void> result = service.addOutfit(appUserOutfit);
//        if (result.isSuccess()) {
//            return new ResponseEntity<>(HttpStatus.CREATED);
//        }
//        return ErrorResponse.build(result);
//    }
//
//    @PutMapping
//    public ResponseEntity<Object> update(@RequestBody UserOutfit appUserOutfit) {
//        Result<Void> result = service.updateOutfit(appUserOutfit);
//        if (result.isSuccess()) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return ErrorResponse.build(result);
//    }
//
//    @DeleteMapping("/{appUserId}/{outfitId}")
//    public ResponseEntity<Void> deleteByKey(@PathVariable int appUserId, @PathVariable int outfitId) {
//        if (service.deleteOutfitByKey(appUserId, outfitId)) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//}
