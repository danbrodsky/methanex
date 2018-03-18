package server.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "rag_status")
public class RagStatus implements Serializable {
    private static final long serialVersionUID = 6090599179808560348L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name")
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
