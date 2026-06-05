package se.handelsbanken.iaem.entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "kuvert", schema = "epatraktor")
public class KuvertEntity implements Serializable {

    @Id
    @Column(name = "kuvert_id", length = 32)
    private String kuvertId;

    @Column(name = "kundnr", length = 32)
    private String kundnr;

    @Column(name = "kundnamn", length = 200)
    private String kundnamn;

    @Column(name = "datum", length = 20)
    private String datum;

    @Column(name = "mall", length = 100)
    private String mall;

    @Column(name = "kanal", length = 100)
    private String kanal;

    @Column(name = "status", length = 50)
    private String status;

    public String getKuvertId() { return kuvertId; }
    public void setKuvertId(String kuvertId) { this.kuvertId = kuvertId; }

    public String getKundnr() { return kundnr; }
    public void setKundnr(String kundnr) { this.kundnr = kundnr; }

    public String getKundnamn() { return kundnamn; }
    public void setKundnamn(String kundnamn) { this.kundnamn = kundnamn; }

    public String getDatum() { return datum; }
    public void setDatum(String datum) { this.datum = datum; }

    public String getMall() { return mall; }
    public void setMall(String mall) { this.mall = mall; }

    public String getKanal() { return kanal; }
    public void setKanal(String kanal) { this.kanal = kanal; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
