package se.handelsbanken.iaem.entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "massutskick", schema = "epatraktor")
public class MassutskickEntity implements Serializable {

    @Id
    @Column(name = "medd_id", length = 50)
    private String meddId;

    @Column(name = "land", length = 100)
    private String land;

    @Column(name = "avsandare", length = 200)
    private String avsandare;

    @Column(name = "amne", length = 500)
    private String amne;

    @Column(name = "utskicksdatum", length = 20)
    private String utskicksdatum;

    @Column(name = "notifieringskategori", length = 200)
    private String notifieringskategori;

    @Column(name = "meddelande", columnDefinition = "TEXT")
    private String meddelande;

    @Column(name = "status", length = 50)
    private String status;

    public String getMeddId() { return meddId; }
    public void setMeddId(String meddId) { this.meddId = meddId; }

    public String getLand() { return land; }
    public void setLand(String land) { this.land = land; }

    public String getAvsandare() { return avsandare; }
    public void setAvsandare(String avsandare) { this.avsandare = avsandare; }

    public String getAmne() { return amne; }
    public void setAmne(String amne) { this.amne = amne; }

    public String getUtskicksdatum() { return utskicksdatum; }
    public void setUtskicksdatum(String utskicksdatum) { this.utskicksdatum = utskicksdatum; }

    public String getNotifieringskategori() { return notifieringskategori; }
    public void setNotifieringskategori(String notifieringskategori) { this.notifieringskategori = notifieringskategori; }

    public String getMeddelande() { return meddelande; }
    public void setMeddelande(String meddelande) { this.meddelande = meddelande; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
