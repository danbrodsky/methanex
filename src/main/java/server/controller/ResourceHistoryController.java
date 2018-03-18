package server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.model.ResourceHistory;
import server.model.Project;
import server.model.Resource;
import server.repository.ResourceHistoryRepository;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class ResourceHistoryController {

    @Autowired
    private ResourceHistoryRepository repository;

    @PostMapping("/resourceHistory")
    public ResponseEntity<ResourceHistory> createResourceHistory(@Valid @RequestBody ResourceHistory resourceHistory) {
        ResourceHistory saved = repository.save(resourceHistory);
        if (saved != null) {
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

    @GetMapping("/resourceHistory/projectId={projectId}&startDuration>{startDuration}&endDuration<{endDuration}")
    public ResponseEntity<List<Resource>> getResourcesFromProjectsBetweenStartAndEndDate(@PathVariable(value = "projectId") Integer projectId,
                                                                                   @PathVariable(value = "startDuration") LocalDate startDuration,
                                                                                   @PathVariable(value = "endDuration") LocalDate endDuration) {
        List<Resource> resources = new ArrayList<>();
        List<ResourceHistory> resourceHistories = repository.findByProjectIdAndStartDurationAfterAndEndDurationBefore(projectId, startDuration, endDuration);
        if (!resourceHistories.isEmpty()) {
            resourceHistories.forEach(resourceHistory -> {
                Resource resource = resourceHistory.getResource();
                if (resource != null) {
                    resources.add(resource);
                }
            });
            return ResponseEntity.ok(resources);
        }
        return ResponseEntity.notFound().build();
    }
}