package server;

import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/notifications/{managerId}")
    public @ResponseBody
    ResponseEntity<List<Skill>> getManagerNotifications(@PathVariable(value = "managerId") Integer managerId) {
        List<Skill> skillNotifications = repository.findSkillNotificationsByResource(managerId);
        if (!skillNotifications.isEmpty()) {
            return ResponseEntity.ok(skillNotifications);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/notifications")
    public void createNotification(@Valid @RequestBody List<Notification> notifications) {
        notifications.forEach(notification -> repository.save(notification));
    }

    @GetMapping("/notifications/{managerId}/{skillId}")
    public ResponseEntity<Notification> getNotification(@PathVariable(value = "managerId") Integer managerId,
                                                 @PathVariable(value = "skillId") Integer skillId) {
        Notification notification = repository.findNotification(managerId, skillId);
        if (notification != null) {
            return ResponseEntity.ok(notification);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/notifications/{managerId}/{skillId}")
    public ResponseEntity<Notification> deleteNotification(@PathVariable(value = "managerId") Integer managerId,
                                                           @PathVariable(value = "skillId") Integer skillId) {
        Notification notification = repository.findNotification(managerId, skillId);
        if (notification != null) {
            repository.deleteNotification(managerId, skillId);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}