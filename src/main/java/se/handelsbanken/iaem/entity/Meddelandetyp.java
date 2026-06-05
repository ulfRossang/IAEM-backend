package se.handelsbanken.iaem.entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "meddelandetyp", schema = "epatraktor")
public class Meddelandetyp implements Serializable {

    @Id
    @Column(name = "persmeddtyp", precision = 2)
    private Integer persmeddtyp;

    @Column(name = "beskrivning", length = 80)
    private String beskrivning;

    public Integer getPersmeddtyp() { return persmeddtyp; }
    public void setPersmeddtyp(Integer persmeddtyp) { this.persmeddtyp = persmeddtyp; }

    public String getBeskrivning() { return beskrivning; }
    public void setBeskrivning(String beskrivning) { this.beskrivning = beskrivning; }
}
