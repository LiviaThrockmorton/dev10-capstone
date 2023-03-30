//package learn.capstone.controllers;
//
//import learn.capstone.domain.CommentService;
//import learn.capstone.domain.Result;
//import learn.capstone.models.Comment;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@CrossOrigin(origins = {"http://localhost:3000"})
//@RequestMapping("/api/comment")
//public class CommentController {
//
//    private final CommentService service;
//
//    public CommentController(CommentService service) {
//        this.service = service;
//    }
//
//    @GetMapping("/{commentId}")
//    public ResponseEntity<Comment> findById(@PathVariable int commentId) {
//        Comment comment = service.findById(commentId);
//        if (comment == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return ResponseEntity.ok(comment);
//    }
//
//    @PostMapping
//    public ResponseEntity<Object> add(@RequestBody Comment comment) {
//        Result<Comment> result = service.add(comment);
//        if (result.isSuccess()) {
//            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
//        }
//        return ErrorResponse.build(result);
//    }
//
//    @PutMapping("/{commentId}")
//    public ResponseEntity<Object> update(@PathVariable int commentId, @RequestBody Comment comment) {
//        if (commentId != comment.getCommentId()) {
//            return new ResponseEntity<>(HttpStatus.CONFLICT);
//        }
//
//        Result<Comment> result = service.update(comment);
//        if (result.isSuccess()) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//
//        return ErrorResponse.build(result);
//    }
//
//    @DeleteMapping("/{commentId}")
//    public ResponseEntity<Void> deleteById(@PathVariable int commentId) {
//        if (service.deleteById(commentId)) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//}
