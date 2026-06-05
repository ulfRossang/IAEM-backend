package se.handelsbanken.iaem.entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "meddelandestatus", schema = "epatraktor")
public class Meddelandestatus implements Serializable {

    @Id
    @Column(name = "persmeddstatus", precision = 2)
    private Integer persmeddstatus;

    @Column(name = "beskrivning", length = 80)
    private String beskrivning;

    public Integer getPersmeddstatus() { return persmeddstatus; }
    public void setPersmeddstatus(Integer persmeddstatus) { this.persmeddstatus = persmeddstatus; }

    public String getBeskrivning() { return beskrivning; }
    public void setBeskrivning(String beskrivning) { this.beskrivning = beskrivning; }
}
