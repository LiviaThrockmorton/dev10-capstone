package learn.capstone.controllers;


import learn.capstone.domain.OutfitService;
import learn.capstone.domain.Result;
import learn.capstone.domain.DuckService;
import learn.capstone.models.AppUser;
import learn.capstone.models.Outfit;
import learn.capstone.models.Duck;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/security/clearance")
public class DuckController {

    private final DuckService service;

    public DuckController(DuckService service) {
        this.service = service;
    }

    @GetMapping
    public List<Duck> findAll() {
        return service.findAll();
    }

    @GetMapping("/{duckId}")
//    public Duck findById(@PathVariable int duckId) {
//        return service.findById(duckId);
//    }
    public ResponseEntity<Duck> findById(@PathVariable int duckId) {
        Duck duck = service.findById(duckId);
        if (duck == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(duck);
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Duck duck) {
        Result<Duck> result = service.add(duck);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @PutMapping("/{duckId}")
    public ResponseEntity<Object> update(@PathVariable int duckId, @RequestBody Duck duck) {
        if (duckId != duck.getDuckId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<Duck> result = service.update(duck);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{duckId}")
    public ResponseEntity<Void> deleteById(@PathVariable int duckId) {
        Result<Duck> result = service.deleteById(duckId);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}