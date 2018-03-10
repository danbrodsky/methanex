package server;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Skill")
public class Skill implements Serializable {
    @GeneratedValue
    @Id
    private int id;

    @Column(name = "name")
    private String name;

    @JoinTable
    @OneToMany
    private List<Category> category;

    @JsonIgnore
    @ManyToMany(mappedBy = "skills")
    private List<Resource> resources = new ArrayList<>();

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

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }
}
