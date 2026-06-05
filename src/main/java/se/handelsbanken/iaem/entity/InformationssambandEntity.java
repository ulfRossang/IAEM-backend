package se.handelsbanken.iaem.entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "informationssamband", schema = "epatraktor")
public class InformationssambandEntity implements Serializable {

    @Id
    @Column(name = "id", length = 32)
    private String id;

    @Column(name = "systembeteckning", length = 100)
    private String systembeteckning;

    @Column(name = "informations_id", length = 100)
    private String informationsId;

    @Column(name = "publicera_automatiskt", precision = 1)
    private Integer publiceraAutomatiskt;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getSystembeteckning() { return systembeteckning; }
    public void setSystembeteckning(String systembeteckning) { this.systembeteckning = systembeteckning; }

    public String getInformationsId() { return informationsId; }
    public void setInformationsId(String informationsId) { this.informationsId = informationsId; }

    public Integer getPubliceraAutomatiskt() { return publiceraAutomatiskt; }
    public void setPubliceraAutomatiskt(Integer publiceraAutomatiskt) { this.publiceraAutomatiskt = publiceraAutomatiskt; }
}
