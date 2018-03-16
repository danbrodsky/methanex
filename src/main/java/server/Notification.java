package server;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Notification")
public class Notification implements Serializable {
    private static final long serialVersionUID = -6616090061386853759L;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;

    @OneToOne
    @JoinColumn(name = "manager_id")
    private Resource manager;

    @OneToOne
    @JoinColumn(name = "skill_id")
    private Skill skill;

    @OneToOne
    @JoinColumn(name = "resource_id")
    private Resource resource;

    public Notification() {}

    public Notification(int managerId, int skillId) {}

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
}
