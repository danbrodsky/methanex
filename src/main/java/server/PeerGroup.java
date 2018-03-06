package server;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "peer_group")
public class PeerGroup implements Serializable {
    @GeneratedValue
    @Id
    private int code;

    private String name;
}
