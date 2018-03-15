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

    public NotificationId(int managerId, int skillId) {
        this.managerId = managerId;
        this.skillId = skillId;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }
}
