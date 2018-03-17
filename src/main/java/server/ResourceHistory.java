package server;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "ResourceHistory")
public class ResourceHistory implements Serializable {
    private static final long serialVersionUID = 2505030243495297377L;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "resource_id", nullable = false)
    private Resource resource;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @Column(name = "dur_start")
    @Temporal(TemporalType.DATE)
    private Date startDuration;

    @Column(name = "dur_end")
    @Temporal(TemporalType.DATE)
    private Date endDuration;

    public Resource getResource() {
        return resource;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Date getStartDuration() {
        return startDuration;
    }

    public void setStartDuration(Date startDuration) {
        this.startDuration = startDuration;
    }

    public Date getEndDuration() {
        return endDuration;
    }

    public void setEndDuration(Date endDuration) {
        this.endDuration = endDuration;
    }
}