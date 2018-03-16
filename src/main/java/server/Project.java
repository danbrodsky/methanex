package server;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

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
    @JsonProperty("id")
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private Resource manager;

    @Column(name = "effort")
    private String effort;

    @Column(name = "dur_start")
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Column(name = "dur_end")
    @Temporal(TemporalType.DATE)
    private Date endDate;

    @Column(name = "budget")
    private Integer budget;

    @Column(name = "status")
    private ProjectStatus status;

    @Column(name="rag_status")
    private Integer ragStatus;

    @Column(name = "exp_pa_status_date")
    @Temporal(TemporalType.DATE)
    private Date expectedPreApprovalStatusDate;

    @Column(name = "exp_seek_funding_status_date")
    @Temporal(TemporalType.DATE)
    private Date expectedSeekFundingStatusDate;

    @Column(name = "exp_pipeline_status_date")
    @Temporal(TemporalType.DATE)
    private Date expectedPipelineStatusDate;

    @Column(name = "exp_confirm_status_date")
    @Temporal(TemporalType.DATE)
    private Date expectedConfirmedStatusDate;

    @Column(name = "exp_closing_status_date")
    @Temporal(TemporalType.DATE)
    private Date expectedClosingStatusDate;

    @Column(name = "exp_closed_status_date")
    @Temporal(TemporalType.DATE)
    private Date expectedClosedStatusDate;

    @Column(name = "actual_pa_status_date")
    @Temporal(TemporalType.DATE)
    private Date actualPreApprovalStatusDate;

    @Column(name = "actual_seek_funding_status_date")
    @Temporal(TemporalType.DATE)
    private Date actualSeekFundingStatusDate;

    @Column(name = "actual_pipeline_status_date")
    @Temporal(TemporalType.DATE)
    private Date actualPipelineStatusDate;

    @Column(name = "actual_confirm_status_date")
    @Temporal(TemporalType.DATE)
    private Date actualConfirmedStatusDate;

    @Column(name = "actual_closing_status_date")
    @Temporal(TemporalType.DATE)
    private Date actualClosingStatusDate;

    @Column(name = "actual_closed_status_date")
    @Temporal(TemporalType.DATE)
    private Date actualClosedStatusDate;

    @Column(name = "percentage_complete")
    private Integer percentageComplete;

    @Column(name = "est_remaining_cost")
    private Integer estimatedRemainingCost;

    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_owner_id")
    private Resource projectOwner;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Portfolio portfolio;

    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "project_resource",
            joinColumns = {@JoinColumn(name = "project_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "resource_id", referencedColumnName = "id")})
    private List<Resource> resources = new ArrayList<>();

    public void addResource(Resource resource) {
        resources.add(resource);
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

    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

    @JsonIgnore
    public Integer getRagStatus() {
        return ragStatus;
    }

    public void setRagStatus(Integer ragStatus) {
        this.ragStatus = ragStatus;
    }

    public Date getExpectedPreApprovalStatusDate() {
        return expectedPreApprovalStatusDate;
    }

    public void setExpectedPreApprovalStatusDate(Date expectedPreApprovalStatusDate) {
        this.expectedPreApprovalStatusDate = expectedPreApprovalStatusDate;
    }

    public Date getExpectedSeekFundingStatusDate() {
        return expectedSeekFundingStatusDate;
    }

    public void setExpectedSeekFundingStatusDate(Date expectedSeekFundingStatusDate) {
        this.expectedSeekFundingStatusDate = expectedSeekFundingStatusDate;
    }

    public Date getExpectedPipelineStatusDate() {
        return expectedPipelineStatusDate;
    }

    public void setExpectedPipelineStatusDate(Date expectedPipelineStatusDate) {
        this.expectedPipelineStatusDate = expectedPipelineStatusDate;
    }

    public Date getExpectedConfirmedStatusDate() {
        return expectedConfirmedStatusDate;
    }

    public void setExpectedConfirmedStatusDate(Date expectedConfirmedStatusDate) {
        this.expectedConfirmedStatusDate = expectedConfirmedStatusDate;
    }

    public Date getExpectedClosingStatusDate() {
        return expectedClosingStatusDate;
    }

    public void setExpectedClosingStatusDate(Date expectedClosingStatusDate) {
        this.expectedClosingStatusDate = expectedClosingStatusDate;
    }

    public Date getExpectedClosedStatusDate() {
        return expectedClosedStatusDate;
    }

    public void setExpectedClosedStatusDate(Date expectedClosedStatusDate) {
        this.expectedClosedStatusDate = expectedClosedStatusDate;
    }

    public Date getActualPreApprovalStatusDate() {
        return actualPreApprovalStatusDate;
    }

    public void setActualPreApprovalStatusDate(Date actualPreApprovalStatusDate) {
        this.actualPreApprovalStatusDate = actualPreApprovalStatusDate;
    }

    public Date getActualSeekFundingStatusDate() {
        return actualSeekFundingStatusDate;
    }

    public void setActualSeekFundingStatusDate(Date actualSeekFundingStatusDate) {
        this.actualSeekFundingStatusDate = actualSeekFundingStatusDate;
    }

    public Date getActualPipelineStatusDate() {
        return actualPipelineStatusDate;
    }

    public void setActualPipelineStatusDate(Date actualPipelineStatusDate) {
        this.actualPipelineStatusDate = actualPipelineStatusDate;
    }

    public Date getActualConfirmedStatusDate() {
        return actualConfirmedStatusDate;
    }

    public void setActualConfirmedStatusDate(Date actualConfirmedStatusDate) {
        this.actualConfirmedStatusDate = actualConfirmedStatusDate;
    }

    public Date getActualClosingStatusDate() {
        return actualClosingStatusDate;
    }

    public void setActualClosingStatusDate(Date actualClosingStatusDate) {
        this.actualClosingStatusDate = actualClosingStatusDate;
    }

    public Date getActualClosedStatusDate() {
        return actualClosedStatusDate;
    }

    public void setActualClosedStatusDate(Date actualClosedStatusDate) {
        this.actualClosedStatusDate = actualClosedStatusDate;
    }

    @JsonIgnore
    public Integer getPercentageComplete() {
        return percentageComplete;
    }

    public void setPercentageComplete(Integer percentageComplete) {
        this.percentageComplete = percentageComplete;
    }

    @JsonIgnore
    public Integer getEstimatedRemainingCost() {
        return estimatedRemainingCost;
    }

    public void setEstimatedRemainingCost(Integer estimatedRemainingCost) {
        this.estimatedRemainingCost = estimatedRemainingCost;
    }

    public Resource getProjectOwner() {
        return projectOwner;
    }

    public void setProjectOwner(Resource projectOwner) {
        this.projectOwner = projectOwner;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }
}
