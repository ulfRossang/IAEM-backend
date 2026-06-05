package se.handelsbanken.iaem.entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "dokument", schema = "epatraktor")
public class DokumentEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "kundnr", length = 32)
    private String kundnr;

    @Column(name = "dokumentnamn", length = 500)
    private String dokumentnamn;

    @Column(name = "forbindelse", length = 100)
    private String forbindelse;

    @Column(name = "dokumentdatum", length = 20)
    private String dokumentdatum;

    @Column(name = "utskicksdatum", length = 20)
    private String utskicksdatum;

    @Column(name = "skickats_till", length = 50)
    private String skickatsTill;

    @Column(name = "visas_till", length = 20)
    private String visasTill;

    @Column(name = "last", precision = 1)
    private Integer last;

    @Column(name = "borttaget", precision = 1)
    private Integer borttaget;

    @Column(name = "arkiverat", precision = 1)
    private Integer arkiverat;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getKundnr() { return kundnr; }
    public void setKundnr(String kundnr) { this.kundnr = kundnr; }

    public String getDokumentnamn() { return dokumentnamn; }
    public void setDokumentnamn(String dokumentnamn) { this.dokumentnamn = dokumentnamn; }

    public String getForbindelse() { return forbindelse; }
    public void setForbindelse(String forbindelse) { this.forbindelse = forbindelse; }

    public String getDokumentdatum() { return dokumentdatum; }
    public void setDokumentdatum(String dokumentdatum) { this.dokumentdatum = dokumentdatum; }

    public String getUtskicksdatum() { return utskicksdatum; }
    public void setUtskicksdatum(String utskicksdatum) { this.utskicksdatum = utskicksdatum; }

    public String getSkickatsTill() { return skickatsTill; }
    public void setSkickatsTill(String skickatsTill) { this.skickatsTill = skickatsTill; }

    public String getVisasTill() { return visasTill; }
    public void setVisasTill(String visasTill) { this.visasTill = visasTill; }

    public Integer getLast() { return last; }
    public void setLast(Integer last) { this.last = last; }

    public Integer getBorttaget() { return borttaget; }
    public void setBorttaget(Integer borttaget) { this.borttaget = borttaget; }

    public Integer getArkiverat() { return arkiverat; }
    public void setArkiverat(Integer arkiverat) { this.arkiverat = arkiverat; }
}
