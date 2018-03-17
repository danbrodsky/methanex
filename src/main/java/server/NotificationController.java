package server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class NotificationController {
    @Autowired
    private NotificationRepository repository;

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private SkillRepository skillRepository;

    @GetMapping("/notifications/manager/{managerId}")
    public @ResponseBody
    ResponseEntity<List<Notification>> getManagerNotifications(@PathVariable(value = "managerId") Integer managerId) {
        List<Notification> notifications = repository.findByManagerId(managerId);
        if (!notifications.isEmpty()) {
            return ResponseEntity.ok(notifications);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/notifications/{resourceId}/{skillId}")
    public ResponseEntity addSkillToResource(@PathVariable(value = "resourceId") Integer resourceId, @PathVariable(value = "skillId") Integer skillId) {
            Resource resource = resourceRepository.findOne(resourceId);
            Skill skill = skillRepository.findOne(skillId);
            if (resource != null && skill != null) {
                resource.addSkill(skill);
                return ResponseEntity.ok().build();
            }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/notifications")
    public ResponseEntity<List<Notification>> getNotification() {
        List<Notification> notifications = repository.findAll();
        if (!notifications.isEmpty()) {
            return new ResponseEntity<>(notifications, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/notifications")
    public ResponseEntity<Notification> createNotification(@Valid @RequestBody List<Notification> notifications) {
        notifications.forEach(notification -> repository.save(notification));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/notifications/{managerId}/{resourceId}/{skillId}")
    public ResponseEntity<Notification> getNotification(@PathVariable(value = "managerId") Integer managerId,
                                                 @PathVariable(value = "skillId") Integer skillId,
                                                 @PathVariable(value = "resourceId") Integer resourceId) {
        Notification notification = repository.findByManagerIdAndResourceIdAndSkillId(managerId, resourceId, skillId);
        if (notification != null) {
            return ResponseEntity.ok(notification);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/notifications/{managerId}/{resourceId}/{skillId}")
    public ResponseEntity<Notification> deleteNotification(@PathVariable(value = "managerId") Integer managerId,
                                                           @PathVariable(value = "skillId") Integer skillId,
                                                           @PathVariable(value = "resourceId") Integer resourceId) {
        Notification notification = repository.findByManagerIdAndResourceIdAndSkillId(managerId, resourceId, skillId);
        if (notification != null) {
//            repository.deleteNotification(managerId, resourceId, skillId);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}