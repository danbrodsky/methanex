package server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class ResourceController {
    @Autowired
    private ResourceRepository repository;

    @PostMapping("/resources")
    public Resource createResource(@Valid @RequestBody Resource resource) {
        return repository.save(resource);
    }

    @GetMapping("/resources")
    public @ResponseBody Iterable<Resource> getAllResources() {
        return repository.findAll();
    }

    @GetMapping("/resources/{resourceId}")
    public @ResponseBody ResponseEntity<Resource> getResource(@PathVariable(value = "resourceId") Integer resourceId) {
        Resource resource = repository.findOne(resourceId);
        if (resource != null) {
            return ResponseEntity.ok(resource);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/resources/{resourceId}")
    public ResponseEntity<Resource> updateResource(@PathVariable(value = "resourceId") Integer resourceId,
                                                   @Valid @RequestBody Resource updateDetails) {
        Resource beforeResource = repository.findOne(resourceId);
        if(beforeResource != null) {
            beforeResource.setName(updateDetails.getName());
            beforeResource.setEmail(updateDetails.getEmail());
            beforeResource.setLocation(updateDetails.getLocation());
            beforeResource.setManager(updateDetails.getManager());
            beforeResource.setStatus(updateDetails.getStatus());

            Resource updatedResource = repository.save(beforeResource);
            return ResponseEntity.ok(updatedResource);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/resources/{resourceId}")
    public ResponseEntity<Resource> deleteResource(@PathVariable(value = "resourceId") Integer resourceId) {
        Resource resource = repository.findOne(resourceId);
        if(resource != null) {
            repository.delete(resource);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/resources/{resourceId}/projects")
    public @ResponseBody
    ResponseEntity<List<Project>> getProjectsFromResource(@PathVariable(value = "resourceId") Integer resourceId) {
        Resource resource = repository.findOne(resourceId);
        if (resource != null) {
            return ResponseEntity.ok(resource.getProjects());
        }
        return ResponseEntity.notFound().build();
    }
}
