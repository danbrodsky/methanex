package server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.model.RagStatus;
import server.repository.RagStatusRepository;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class RagStatusController {
    @Autowired
    private RagStatusRepository repository;
    @GetMapping("/ragstatuses")
    public ResponseEntity<List<RagStatus>> getRagStatuses() {
        return ResponseEntity.ok(repository.findAll());
    }
}
