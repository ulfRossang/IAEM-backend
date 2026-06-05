package se.handelsbanken.iaem.entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "utskick_bilaga", schema = "epatraktor")
public class UtskickBilagaEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "utskick_id")
    private UtskickEntity utskick;

    @Column(name = "filnamn", length = 500)
    private String filnamn;

    @Column(name = "url", length = 500)
    private String url;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public UtskickEntity getUtskick() { return utskick; }
    public void setUtskick(UtskickEntity utskick) { this.utskick = utskick; }

    public String getFilnamn() { return filnamn; }
    public void setFilnamn(String filnamn) { this.filnamn = filnamn; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
}
