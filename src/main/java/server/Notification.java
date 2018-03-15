package server;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Notification")
public class Notification implements Serializable {
    private static final long serialVersionUID = -6616090061386853759L;

    @EmbeddedId
    private NotificationId notificationId;

    @MapsId("manager_id")
    @OneToOne
    @JoinColumn(name = "manager_id", referencedColumnName = "id")
    private Resource manager;

    @MapsId("skill_id")
    @OneToOne
    @JoinColumn(name = "skill_id", referencedColumnName = "id")
    private Skill skill;

    @MapsId("resource_id")
    @OneToOne
    @JoinColumn(name = "resource_id", referencedColumnName = "id")
    private Resource resource;

    public Notification(int managerId, int resourceId, int skillId) {}

    public Resource getManager() {
        return manager;
    }

    public void setManager(Resource manager) {
        this.manager = manager;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public NotificationId getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(NotificationId notificationId) {
        this.notificationId = notificationId;
    }
}
