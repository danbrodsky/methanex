package server;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Portfolio")
public class Portfolio implements Serializable {
    private static final long serialVersionUID = 6289737580166584653L;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @JsonProperty("id")
    private int id;

    @OneToOne
    @JoinColumn(name = "classification_id", nullable = false)
    private Classification classification;

    @Column(name = "resource_breakdown")
    private String resourceBreakdown;

    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @ManyToMany(mappedBy = "portfolios", fetch = FetchType.LAZY)
    private List<Project> projects = new ArrayList<>();

    public Classification getClassification() {
        return classification;
    }

    public void setClassification(Classification classification) {
        this.classification = classification;
    }

    public String getResourceBreakdown() {
        return resourceBreakdown;
    }

    public void setResourceBreakdown(String resourceBreakdown) {
        this.resourceBreakdown = resourceBreakdown;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}