package server;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class NotificationId implements Serializable {
    @Column(name = "manager_id")
    private int managerId;

    @Column(name = "skill_id")
    private int skillId;
}
