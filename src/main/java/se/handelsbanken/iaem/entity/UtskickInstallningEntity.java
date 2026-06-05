package se.handelsbanken.iaem.entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "utskick_installning", schema = "epatraktor")
public class UtskickInstallningEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "kundnr", length = 32)
    private String kundnr;

    @Column(name = "kategori", length = 100)
    private String kategori;

    @Column(name = "avser", length = 100)
    private String avser;

    @Column(name = "forbindelse", length = 100)
    private String forbindelse;

    @Column(name = "papper", precision = 1)
    private Integer papper;

    @Column(name = "internet", precision = 1)
    private Integer internet;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getKundnr() { return kundnr; }
    public void setKundnr(String kundnr) { this.kundnr = kundnr; }

    public String getKategori() { return kategori; }
    public void setKategori(String kategori) { this.kategori = kategori; }

    public String getAvser() { return avser; }
    public void setAvser(String avser) { this.avser = avser; }

    public String getForbindelse() { return forbindelse; }
    public void setForbindelse(String forbindelse) { this.forbindelse = forbindelse; }

    public Integer getPapper() { return papper; }
    public void setPapper(Integer papper) { this.papper = papper; }

    public Integer getInternet() { return internet; }
    public void setInternet(Integer internet) { this.internet = internet; }
}
