package server;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Resource")
public class Resource implements Serializable {
    private static final long serialVersionUID = -8230906242675537492L;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "location", nullable = false)
    private String location;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id", nullable = false)
    private Resource manager;

    @OneToOne
    @JoinColumn(name = "status_id")
    private ResourceStatus status;

    protected Resource() {
    }

    public Resource(int id, String name, String email, String location, Resource manager, ResourceStatus status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.location = location;
        this.manager = manager;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Resource getManager() {
        return manager;
    }

    public void setManager(Resource manager) {
        this.manager = manager;
    }

    public ResourceStatus getStatus() {
        return status;
    }

    public void setStatus(ResourceStatus status) {
        this.status = status;
    }
}
