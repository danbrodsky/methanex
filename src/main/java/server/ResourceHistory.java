package server;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "resource_history")
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
    private LocalDate startDuration;

    @Column(name = "dur_end")
    private LocalDate endDuration;

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

    public LocalDate getStartDuration() {
        return startDuration;
    }

    public void setStartDuration(LocalDate startDuration) {
        this.startDuration = startDuration;
    }

    public LocalDate getEndDuration() {
        return endDuration;
    }

    public void setEndDuration(LocalDate endDuration) {
        this.endDuration = endDuration;
    }
}