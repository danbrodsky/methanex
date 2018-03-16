package server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class ResourceHistoryController {

    @Autowired
    private ResourceHistoryRepository repository;

    @PostMapping("/resourceHistory")
    public ResponseEntity<ResourceHistory> addResourceToProject(@Valid @RequestBody ResourceHistory resourceHistory) {
        Project project = resourceHistory.getProject();
        Resource resource = resourceHistory.getResource();
        if (project != null && resource != null) {
            project.addResource(resource);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/resourceHistory")
    public ResponseEntity<Project> deleteProject(@Valid @RequestBody ResourceHistory resourceHistory) {
        if (resourceHistory != null) {
            repository.delete(resourceHistory);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
