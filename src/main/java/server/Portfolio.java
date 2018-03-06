package server;

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
    private int id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Classification", nullable = false)
    private Classification classification;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id", nullable = false)
    private PortfolioStatus status;

    @Column(name = "resource_breakdown")
    private String resourceBreakdown;

    @Column(name = "business_owner_id", nullable = false)
    private BusinessOwner businessOwner;

    @ManyToMany(mappedBy = "portfolios")
    private List<Project> projects = new ArrayList<>();

    public Portfolio() {}

    public Portfolio(Classification classification, PortfolioStatus status, String resourceBreakdown, BusinessOwner businessOwner, List<Project> projects) {
        this.classification = classification;
        this.status = status;
        this.resourceBreakdown = resourceBreakdown;
        this.businessOwner = businessOwner;
        this.projects = projects;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Classification getClassification() {
        return classification;
    }

    public void setClassification(Classification classification) {
        this.classification = classification;
    }

    public PortfolioStatus getStatus() {
        return status;
    }

    public void setStatus(PortfolioStatus status) {
        this.status = status;
    }

    public String getResourceBreakdown() {
        return resourceBreakdown;
    }

    public void setResourceBreakdown(String resourceBreakdown) {
        this.resourceBreakdown = resourceBreakdown;
    }

    public BusinessOwner getBusinessOwner() {
        return businessOwner;
    }

    public void setBusinessOwner(BusinessOwner businessOwner) {
        this.businessOwner = businessOwner;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
}
