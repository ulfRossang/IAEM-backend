package se.handelsbanken.iaem.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "utskick", schema = "epatraktor")
public class UtskickEntity implements Serializable {

    @Id
    @Column(name = "utskick_id", length = 32)
    private String utskickId;

    @Column(name = "kundnr", length = 32)
    private String kundnr;

    @Column(name = "kundnamn", length = 200)
    private String kundnamn;

    @Column(name = "avsandare", length = 200)
    private String avsandare;

    @Column(name = "mottagare", length = 200)
    private String mottagare;

    @Column(name = "datum")
    private LocalDateTime datum;

    @Column(name = "kategori", length = 100)
    private String kategori;

    @Column(name = "amne", length = 500)
    private String amne;

    @Column(name = "las", precision = 1)
    private Integer las;

    @Column(name = "borttaget", precision = 1)
    private Integer borttaget;

    @Column(name = "arkiverat", precision = 1)
    private Integer arkiverat;

    @Column(name = "visas_till", length = 20)
    private String visasTill;

    @Column(name = "innehall", columnDefinition = "TEXT")
    private String innehall;

    @OneToMany(mappedBy = "utskick", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<UtskickBilagaEntity> bilagor = new ArrayList<>();

    public String getUtskickId() { return utskickId; }
    public void setUtskickId(String utskickId) { this.utskickId = utskickId; }

    public String getKundnr() { return kundnr; }
    public void setKundnr(String kundnr) { this.kundnr = kundnr; }

    public String getKundnamn() { return kundnamn; }
    public void setKundnamn(String kundnamn) { this.kundnamn = kundnamn; }

    public String getAvsandare() { return avsandare; }
    public void setAvsandare(String avsandare) { this.avsandare = avsandare; }

    public String getMottagare() { return mottagare; }
    public void setMottagare(String mottagare) { this.mottagare = mottagare; }

    public LocalDateTime getDatum() { return datum; }
    public void setDatum(LocalDateTime datum) { this.datum = datum; }

    public String getKategori() { return kategori; }
    public void setKategori(String kategori) { this.kategori = kategori; }

    public String getAmne() { return amne; }
    public void setAmne(String amne) { this.amne = amne; }

    public Integer getLas() { return las; }
    public void setLas(Integer las) { this.las = las; }

    public Integer getBorttaget() { return borttaget; }
    public void setBorttaget(Integer borttaget) { this.borttaget = borttaget; }

    public Integer getArkiverat() { return arkiverat; }
    public void setArkiverat(Integer arkiverat) { this.arkiverat = arkiverat; }

    public String getVisasTill() { return visasTill; }
    public void setVisasTill(String visasTill) { this.visasTill = visasTill; }

    public String getInnehall() { return innehall; }
    public void setInnehall(String innehall) { this.innehall = innehall; }

    public List<UtskickBilagaEntity> getBilagor() { return bilagor; }
    public void setBilagor(List<UtskickBilagaEntity> bilagor) { this.bilagor = bilagor; }
}
