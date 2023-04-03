package learn.capstone.controllers;


import learn.capstone.domain.ClothingItemService;
import learn.capstone.domain.Result;
import learn.capstone.models.ClothingItem;
import learn.capstone.models.Outfit;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/item")
public class ClothingItemController {

    private final ClothingItemService service;

    public ClothingItemController(ClothingItemService service) {
        this.service = service;
    }

    @GetMapping("/type/{itemType}")
    public List<ClothingItem> findByType(@PathVariable String itemType) {
        return service.findByType(itemType);
    }

    @GetMapping("/{itemId}")
    public ClothingItem findById(@PathVariable int itemId) {
        return service.findById(itemId);
    }


    @PostMapping
    public ResponseEntity<Object> add(@RequestBody ClothingItem item) {
        Result<ClothingItem> result = service.add(item);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @PutMapping("/{itemId}")
    public ResponseEntity<Object> update(@PathVariable int itemId, @RequestBody ClothingItem item) {
        if (itemId != item.getItemId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Result<ClothingItem> result = service.update(item);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<Object> deleteById(@PathVariable int itemId, @RequestBody ClothingItem item) {
        if (itemId != item.getItemId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Result<ClothingItem> result = service.update(item);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ErrorResponse.build(result);

    }
}