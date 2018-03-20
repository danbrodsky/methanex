package server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "technical_skill")
public class TechnicalSkill implements Serializable {
    private static final long serialVersionUID = 6254557214609163391L;

    @GeneratedValue
    @Id
    private int id;

    @Column(name = "name", unique = true)
    private String name;

    @JoinTable
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Category> category;

    @JsonIgnore
    @ManyToMany(mappedBy = "skills", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Resource> resources = new HashSet<>();

    void addResource(Resource resource) {
        if (!resources.contains(resource)) {
            resources.add(resource);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Category> getCategory() {
        return category;
    }

    public void setCategory(List<Category> category) {
        this.category = category;
    }

    public Set<Resource> getResources() {
        return resources;
    }

    public void setResources(Set<Resource> resources) {
        this.resources = resources;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
