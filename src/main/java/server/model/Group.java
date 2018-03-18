package server.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Team")
public class Group implements Serializable {
    @GeneratedValue
    @Id
    private int code;

    private String name;
}
