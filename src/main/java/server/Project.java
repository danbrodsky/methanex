package server;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Project")
public class Project implements Serializable {
    private static final long serialVersionUID = -1250060866739847354L;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private Resource manager;

    @Column(name = "effort")
    private String effort;

    @Column(name = "dur_start")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Column(name = "dur_end")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    @Column(name = "budget")
    private Integer budget;

    @Column(name="rag_status")
    private Integer ragStatus;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "project_portfolio",
            joinColumns = {@JoinColumn(name = "project_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "portfolio_id", referencedColumnName = "id")})
    private List<Portfolio> portfolios = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "project_resource",
            joinColumns = {@JoinColumn(name = "project_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "resource_id", referencedColumnName = "id")})
    private List<Resource> resources = new ArrayList<>();

    protected Project() {}

    public Project(String name, Date startDate, Date endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void addPortfolio(Portfolio portfolio) {
        if (!this.portfolios.isEmpty()) {
            this.portfolios.add(portfolio);
        }
    }

    public void removePortfolio(Portfolio portfolio) {
        if (!this.portfolios.isEmpty()) {
            this.portfolios.remove(portfolio);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Resource getManager() {
        return manager;
    }

    public void setManager(Resource manager) {
        this.manager = manager;
    }

    public String getEffort() {
        return effort;
    }

    public void setEffort(String effort) {
        this.effort = effort;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public Integer getRagStatus() {
        return ragStatus;
    }

    public void setRagStatus(int ragStatus) {
        this.ragStatus = ragStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    public void setRagStatus(Integer ragStatus) {
        this.ragStatus = ragStatus;
    }

    public List<Portfolio> getPortfolios() {
        return portfolios;
    }

    public void setPortfolios(List<Portfolio> portfolios) {
        this.portfolios = portfolios;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }
}
