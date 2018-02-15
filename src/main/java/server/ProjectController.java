package server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class ProjectController {

    @Autowired
    private ProjectRepository repository;

    @PostMapping("/projects")
    public Project createProject(@Valid @RequestBody Project project) {
        return repository.save(project);
    }

    @GetMapping("/projects")
    public @ResponseBody Iterable<Project> getAllProjects() {
        Iterable<Project> projects = repository.findAll();
        return projects;
    }

    @GetMapping("/projects/{projectId}")
    public @ResponseBody ResponseEntity<Project> getProject(@PathVariable(value = "projectId") Integer projectId) {
        Project project = repository.findOne(projectId);
        if (project != null) {
            return ResponseEntity.ok(project);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/projects/{projectId}")
    public ResponseEntity<Project> updateProject(@PathVariable(value = "projectId") Integer projectId,
                                                   @Valid @RequestBody Project updateDetails) {
        Project beforeProject = repository.findOne(projectId);
        if(beforeProject != null) {
            beforeProject.setName(updateDetails.getName());
            beforeProject.setBudget(updateDetails.getBudget());
            beforeProject.setEffort(updateDetails.getEffort());
            beforeProject.setManager(updateDetails.getManager());
            beforeProject.setStartDate(updateDetails.getStartDate());
            beforeProject.setRagStatus(updateDetails.getRagStatus());
            beforeProject.setPortfolio(updateDetails.getPortfolio());

            Project updatedProject = repository.save(beforeProject);
            return ResponseEntity.ok(updatedProject);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/projects/{projectId}")
    public ResponseEntity<Project> deleteProject(@PathVariable(value = "projectId") Integer projectId) {
        Project project = repository.findOne(projectId);
        if(project != null) {
            repository.delete(project);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
