package server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.model.Classification;
import server.repository.ClassificationRepository;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class ClassificationController {
    @Autowired
    private ClassificationRepository repository;

    @GetMapping("/classifications")
    public List<Classification> getClassifications() {
        return repository.findAll();
    }

    @PostMapping("/classifications")
    public ResponseEntity postClassification(@RequestBody Classification classification) {
        repository.save(classification);
        return ResponseEntity.ok().build();
    }
}
