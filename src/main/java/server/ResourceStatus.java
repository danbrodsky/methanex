package server;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "resource_status")
public class ResourceStatus implements Serializable {
    private static final long serialVersionUID = 1735306186219229333L;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

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
}
