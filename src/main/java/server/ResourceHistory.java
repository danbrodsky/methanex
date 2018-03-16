package server;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "ResourceHistory")
public class ResourceHistory implements Serializable {
    private static final long serialVersionUID = 2505030243495297377L;

    @OneToOne(fetch = FetchType.LAZY)
    @Column(name = "resource_id")
    private Resource resource;

    @OneToOne
    @Column(name = "project_id")
    private Project project;

    @Column(name = "dur_start")
    private Date startDuration;

    @Column(name = "dur_end")
    private Date endDuration;

    public Resource getResource() {
        return resource;
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