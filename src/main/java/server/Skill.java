package server;

import javax.persistence.*;
import java.io.Serializable;
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
}
