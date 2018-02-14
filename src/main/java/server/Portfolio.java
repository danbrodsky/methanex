package server;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Portfolio")
public class Portfolio implements Serializable {
    private static final long serialVersionUID = 6289737580166584653L;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Classification", nullable = false)
    private Classification classification;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id", nullable = false)
    private PortfolioStatus status;

    @Column(name = "resource_breakdown")
    private String resourceBreakdown;

    @Column(name = "business_owner", nullable = false)
    private BusinessOwner businessOwner;
}
