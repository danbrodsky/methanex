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

    @GetMapping("/notifications/manager/{managerId}")
    public @ResponseBody
    ResponseEntity<List<Notification>> getManagerNotifications(@PathVariable(value = "managerId") Integer managerId) {
        List<Notification> notifications = repository.findNotificationsByManager(managerId);
        if (!notifications.isEmpty()) {
            return ResponseEntity.ok(notifications);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/notifications/resource/{resourceId}")
    public @ResponseBody
    ResponseEntity<List<Skill>> getResourceNotifications(@PathVariable(value = "resourceId") Integer resourceId) {
        List<Skill> skillNotifications = repository.findSkillNotificationsByResource(resourceId);
        if (!skillNotifications.isEmpty()) {
            return ResponseEntity.ok(skillNotifications);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/notifications")
    public void createNotification(@Valid @RequestBody List<Notification> notifications) {
        notifications.forEach(notification -> repository.save(notification));
    }

    @GetMapping("/notifications/{managerId}/{resourceId}/{skillId}")
    public ResponseEntity<Notification> getNotification(@PathVariable(value = "managerId") Integer managerId,
                                                 @PathVariable(value = "skillId") Integer skillId,
                                                 @PathVariable(value = "resourceId") Integer resourceId) {
        Notification notification = repository.findNotification(managerId, skillId);
        if (notification != null) {
            return ResponseEntity.ok(notification);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/notifications/{managerId}/{resourceId}/{skillId}")
    public ResponseEntity<Notification> deleteNotification(@PathVariable(value = "managerId") Integer managerId,
                                                           @PathVariable(value = "skillId") Integer skillId,
                                                           @PathVariable(value = "resourceId") Integer resourceId) {
        Notification notification = repository.findNotification(managerId, skillId);
        if (notification != null) {
            repository.deleteNotification(managerId, resourceId, skillId);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}