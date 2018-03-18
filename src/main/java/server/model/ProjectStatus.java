package server.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "project_status")
public class ProjectStatus implements Serializable {
    private static final long serialVersionUID = -6724774869582904321L;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;

    @Column(name = "name")
    private String name;
}
