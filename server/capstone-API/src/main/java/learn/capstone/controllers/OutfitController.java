package learn.capstone.controllers;

import learn.capstone.domain.OutfitService;
import learn.capstone.domain.Result;
import learn.capstone.models.Outfit;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/outfit")
public class OutfitController {

    private final OutfitService service;

    public OutfitController(OutfitService service) {
        this.service = service;
    }

    @GetMapping
    public List<Outfit> findAll() {
        return service.findAll();
    }

    @GetMapping("/{outfitId}")
    public Outfit findById(@PathVariable int outfitId) {
        return service.findById(outfitId);
    }

    @GetMapping("/user/{userId}")
    public List <Outfit> findByUser(@PathVariable int userId) {
        return service.findByUser(userId);
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Outfit outfit) {
        Result<Outfit> result = service.add(outfit);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @PutMapping("/{outfitId}")
    public ResponseEntity<Object> update(@PathVariable int outfitId, @RequestBody Outfit outfit) {
        if (outfitId != outfit.getOutfitId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<Outfit> result = service.update(outfit);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{outfitId}")
    public ResponseEntity<Void> deleteById(@PathVariable int outfitId) {
        if (service.deleteById(outfitId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
