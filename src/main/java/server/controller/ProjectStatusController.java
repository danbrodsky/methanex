package server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.model.ProjectStatus;
import server.repository.ProjectStatusRepository;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class ProjectStatusController {
    @Autowired
    private ProjectStatusRepository repository;
    @GetMapping("/projectstatuses")
    public ResponseEntity<List<ProjectStatus>> getStatuses() {
        return ResponseEntity.ok(repository.findAll());
    }
}
