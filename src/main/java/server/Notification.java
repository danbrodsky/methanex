package server;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Notification")
public class Notification implements Serializable {
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
}
