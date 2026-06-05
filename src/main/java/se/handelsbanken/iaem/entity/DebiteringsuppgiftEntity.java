package se.handelsbanken.iaem.entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "debiteringsuppgift", schema = "epatraktor")
public class DebiteringsuppgiftEntity implements Serializable {

    @Id
    @Column(name = "produktid", length = 50)
    private String produktid;

    @Column(name = "meddelandeid", length = 50)
    private String meddelandeid;

    @Column(name = "systembeteckning", length = 50)
    private String systembeteckning;

    @Column(name = "ants_kod_internet", length = 50)
    private String antsKodInternet;

    @Column(name = "ants_kod_ej_internet", length = 50)
    private String antsKodEjInternet;

    @Column(name = "resultatstalle", length = 50)
    private String resultatstalle;

    public String getProduktid() { return produktid; }
    public void setProduktid(String produktid) { this.produktid = produktid; }

    public String getMeddelandeid() { return meddelandeid; }
    public void setMeddelandeid(String meddelandeid) { this.meddelandeid = meddelandeid; }

    public String getSystembeteckning() { return systembeteckning; }
    public void setSystembeteckning(String systembeteckning) { this.systembeteckning = systembeteckning; }

    public String getAntsKodInternet() { return antsKodInternet; }
    public void setAntsKodInternet(String antsKodInternet) { this.antsKodInternet = antsKodInternet; }

    public String getAntsKodEjInternet() { return antsKodEjInternet; }
    public void setAntsKodEjInternet(String antsKodEjInternet) { this.antsKodEjInternet = antsKodEjInternet; }

    public String getResultatstalle() { return resultatstalle; }
    public void setResultatstalle(String resultatstalle) { this.resultatstalle = resultatstalle; }
}
