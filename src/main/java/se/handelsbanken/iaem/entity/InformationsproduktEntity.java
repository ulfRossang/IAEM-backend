package se.handelsbanken.iaem.entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "informationsprodukt", schema = "epatraktor")
public class InformationsproduktEntity implements Serializable {

    @Id
    @Column(name = "id", length = 32)
    private String id;

    @Column(name = "namn", length = 200)
    private String namn;

    @Column(name = "land", length = 100)
    private String land;

    @Column(name = "status", length = 50)
    private String status;

    @Column(name = "notifieringskategori", length = 200)
    private String notifieringskategori;

    @Column(name = "systembeteckning", length = 50)
    private String systembeteckning;

    @Column(name = "insynsskyddad", precision = 1)
    private Integer insynsskyddad;

    @Column(name = "default_papper", precision = 1)
    private Integer defaultPapper;

    @Column(name = "default_internet", precision = 1)
    private Integer defaultInternet;

    @Column(name = "tilllatna_papper", precision = 1)
    private Integer tilllåtnaPapper;

    @Column(name = "tilllatna_internet", precision = 1)
    private Integer tilllåtnaInternet;

    @Column(name = "obligatoriska_papper", precision = 1)
    private Integer obligatoriskaPapper;

    @Column(name = "obligatoriska_internet", precision = 1)
    private Integer obligatoriskaInternet;

    @Column(name = "avgiftsid_papper", length = 50)
    private String avgiftsidPapper;

    @Column(name = "avgiftsid_internet", length = 50)
    private String avgiftsidInternet;

    @Column(name = "visningstid_earkiv_man")
    private Integer visningstidEArkivMan;

    @Column(name = "lagringstid_disk_man")
    private Integer lagringstidDiskMan;

    @Column(name = "meddelandetext", columnDefinition = "TEXT")
    private String meddelandetext;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNamn() { return namn; }
    public void setNamn(String namn) { this.namn = namn; }

    public String getLand() { return land; }
    public void setLand(String land) { this.land = land; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getNotifieringskategori() { return notifieringskategori; }
    public void setNotifieringskategori(String notifieringskategori) { this.notifieringskategori = notifieringskategori; }

    public String getSystembeteckning() { return systembeteckning; }
    public void setSystembeteckning(String systembeteckning) { this.systembeteckning = systembeteckning; }

    public Integer getInsynsskyddad() { return insynsskyddad; }
    public void setInsynsskyddad(Integer insynsskyddad) { this.insynsskyddad = insynsskyddad; }

    public Integer getDefaultPapper() { return defaultPapper; }
    public void setDefaultPapper(Integer defaultPapper) { this.defaultPapper = defaultPapper; }

    public Integer getDefaultInternet() { return defaultInternet; }
    public void setDefaultInternet(Integer defaultInternet) { this.defaultInternet = defaultInternet; }

    public Integer getTilllåtnaPapper() { return tilllåtnaPapper; }
    public void setTilllåtnaPapper(Integer tilllåtnaPapper) { this.tilllåtnaPapper = tilllåtnaPapper; }

    public Integer getTilllåtnaInternet() { return tilllåtnaInternet; }
    public void setTilllåtnaInternet(Integer tilllåtnaInternet) { this.tilllåtnaInternet = tilllåtnaInternet; }

    public Integer getObligatoriskaPapper() { return obligatoriskaPapper; }
    public void setObligatoriskaPapper(Integer obligatoriskaPapper) { this.obligatoriskaPapper = obligatoriskaPapper; }

    public Integer getObligatoriskaInternet() { return obligatoriskaInternet; }
    public void setObligatoriskaInternet(Integer obligatoriskaInternet) { this.obligatoriskaInternet = obligatoriskaInternet; }

    public String getAvgiftsidPapper() { return avgiftsidPapper; }
    public void setAvgiftsidPapper(String avgiftsidPapper) { this.avgiftsidPapper = avgiftsidPapper; }

    public String getAvgiftsidInternet() { return avgiftsidInternet; }
    public void setAvgiftsidInternet(String avgiftsidInternet) { this.avgiftsidInternet = avgiftsidInternet; }

    public Integer getVisningstidEArkivMan() { return visningstidEArkivMan; }
    public void setVisningstidEArkivMan(Integer visningstidEArkivMan) { this.visningstidEArkivMan = visningstidEArkivMan; }

    public Integer getLagringstidDiskMan() { return lagringstidDiskMan; }
    public void setLagringstidDiskMan(Integer lagringstidDiskMan) { this.lagringstidDiskMan = lagringstidDiskMan; }

    public String getMeddelandetext() { return meddelandetext; }
    public void setMeddelandetext(String meddelandetext) { this.meddelandetext = meddelandetext; }
}
