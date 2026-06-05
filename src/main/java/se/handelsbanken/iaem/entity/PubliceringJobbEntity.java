package se.handelsbanken.iaem.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "publicering_jobb", schema = "epatraktor")
public class PubliceringJobbEntity implements Serializable {

    @Id
    @Column(name = "jobb_id", length = 32)
    private String jobbId;

    @Column(name = "systembeteckning", length = 100)
    private String systembeteckning;

    @Column(name = "informations_id", length = 100)
    private String informationsId;

    @Column(name = "leveranstidpunkt")
    private LocalDateTime leveranstidpunkt;

    @Column(name = "status", length = 50)
    private String status;

    public String getJobbId() { return jobbId; }
    public void setJobbId(String jobbId) { this.jobbId = jobbId; }

    public String getSystembeteckning() { return systembeteckning; }
    public void setSystembeteckning(String systembeteckning) { this.systembeteckning = systembeteckning; }

    public String getInformationsId() { return informationsId; }
    public void setInformationsId(String informationsId) { this.informationsId = informationsId; }

    public LocalDateTime getLeveranstidpunkt() { return leveranstidpunkt; }
    public void setLeveranstidpunkt(LocalDateTime leveranstidpunkt) { this.leveranstidpunkt = leveranstidpunkt; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
