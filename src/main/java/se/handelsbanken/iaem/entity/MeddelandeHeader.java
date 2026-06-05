package se.handelsbanken.iaem.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "meddelande_header", schema = "epatraktor")
public class MeddelandeHeader implements Serializable {

    @Id
    @Column(name = "persmeddid", length = 32)
    private String persmeddid;

    @Column(name = "tread", length = 32)
    private String tread;

    @Column(name = "skapdatum")
    private LocalDateTime skapdatum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "persmeddtyp")
    private Meddelandetyp meddelandetyp;

    @Column(name = "mottagare", length = 20)
    private String mottagare;

    @Column(name = "avsandare", length = 20)
    private String avsandare;

    @Column(name = "avsandarklartext", length = 200)
    private String avsandarklartext;

    @Column(name = "borttagetvmot", precision = 1)
    private Integer borttagetvmot;

    @Column(name = "borttagetvavs", precision = 1)
    private Integer borttagetvavs;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "persmeddstatus")
    private Meddelandestatus meddelandestatus;

    @Column(name = "statusfrandatum")
    private LocalDateTime statusfrandatum;

    @Column(name = "handlaggare", length = 8)
    private String handlaggare;

    @Column(name = "svarsmottagare", precision = 15)
    private Long svarsmottagare;

    @Column(name = "rubrik", length = 320)
    private String rubrik;

    @Column(name = "kundnummer", length = 32)
    private String kundnummer;

    @Column(name = "maxmeddelandeid", length = 32)
    private String maxmeddelandeid;

    @Column(name = "clearingnummer", length = 32)
    private String clearingnummer;

    @Column(name = "mottagarlarlest")
    private LocalDateTime mottagarlarlest;

    @Column(name = "mottagarklartext", length = 200)
    private String mottagarklartext;

    @Column(name = "meddelandeordernr", precision = 4)
    private Integer meddelandeordernr;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meddfrågekategori")
    private Fragekategori fragekategori;

    @Column(name = "mottagarekund", precision = 1)
    private Integer mottagarekund;

    @Column(name = "avsandarekund", precision = 1)
    private Integer avsandarekund;

    @Column(name = "insynsskyddat", precision = 1)
    private Integer insynsskyddat;

    @Column(name = "arkiverat", precision = 1)
    private Integer arkiverat;

    @Column(name = "kanalinredd", precision = 1)
    private Integer kanalinredd;

    @Column(name = "bilaga", precision = 1)
    private Integer bilaga;

    @Column(name = "clearingrefid", length = 11)
    private String clearingrefid;

    @Column(name = "visastill")
    private LocalDateTime visastill;

    @Column(name = "isocountrycode", length = 3)
    private String isocountrycode;

    @Column(name = "assgmid", length = 70)
    private String assgmid;

    @Column(name = "rubrik_nr", precision = 5)
    private Integer rubrikNr;

    @Column(name = "meddeland_nr", precision = 5)
    private Integer meddelandNr;

    @Column(name = "mediatyp", length = 1)
    private String mediatyp;

    @Column(name = "las", precision = 1)
    private Integer las;

    @Column(name = "kategori", length = 100)
    private String kategori;

    @Column(name = "kontor", length = 32)
    private String kontor;

    @Column(name = "kundnamn", length = 200)
    private String kundnamn;

    @OneToOne(mappedBy = "header", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Meddelandetext meddelandetext;

    public String getPersmeddid() { return persmeddid; }
    public void setPersmeddid(String persmeddid) { this.persmeddid = persmeddid; }

    public String getTread() { return tread; }
    public void setTread(String tread) { this.tread = tread; }

    public LocalDateTime getSkapdatum() { return skapdatum; }
    public void setSkapdatum(LocalDateTime skapdatum) { this.skapdatum = skapdatum; }

    public Meddelandetyp getMeddelandetyp() { return meddelandetyp; }
    public void setMeddelandetyp(Meddelandetyp meddelandetyp) { this.meddelandetyp = meddelandetyp; }

    public String getMottagare() { return mottagare; }
    public void setMottagare(String mottagare) { this.mottagare = mottagare; }

    public String getAvsandare() { return avsandare; }
    public void setAvsandare(String avsandare) { this.avsandare = avsandare; }

    public String getAvsandarklartext() { return avsandarklartext; }
    public void setAvsandarklartext(String avsandarklartext) { this.avsandarklartext = avsandarklartext; }

    public Integer getBorttagetvmot() { return borttagetvmot; }
    public void setBorttagetvmot(Integer borttagetvmot) { this.borttagetvmot = borttagetvmot; }

    public Integer getBorttagetvavs() { return borttagetvavs; }
    public void setBorttagetvavs(Integer borttagetvavs) { this.borttagetvavs = borttagetvavs; }

    public Meddelandestatus getMeddelandestatus() { return meddelandestatus; }
    public void setMeddelandestatus(Meddelandestatus meddelandestatus) { this.meddelandestatus = meddelandestatus; }

    public LocalDateTime getStatusfrandatum() { return statusfrandatum; }
    public void setStatusfrandatum(LocalDateTime statusfrandatum) { this.statusfrandatum = statusfrandatum; }

    public String getHandlaggare() { return handlaggare; }
    public void setHandlaggare(String handlaggare) { this.handlaggare = handlaggare; }

    public Long getSvarsmottagare() { return svarsmottagare; }
    public void setSvarsmottagare(Long svarsmottagare) { this.svarsmottagare = svarsmottagare; }

    public String getRubrik() { return rubrik; }
    public void setRubrik(String rubrik) { this.rubrik = rubrik; }

    public String getKundnummer() { return kundnummer; }
    public void setKundnummer(String kundnummer) { this.kundnummer = kundnummer; }

    public String getMaxmeddelandeid() { return maxmeddelandeid; }
    public void setMaxmeddelandeid(String maxmeddelandeid) { this.maxmeddelandeid = maxmeddelandeid; }

    public String getClearingnummer() { return clearingnummer; }
    public void setClearingnummer(String clearingnummer) { this.clearingnummer = clearingnummer; }

    public LocalDateTime getMottagarlarlest() { return mottagarlarlest; }
    public void setMottagarlarlest(LocalDateTime mottagarlarlest) { this.mottagarlarlest = mottagarlarlest; }

    public String getMottagarklartext() { return mottagarklartext; }
    public void setMottagarklartext(String mottagarklartext) { this.mottagarklartext = mottagarklartext; }

    public Integer getMeddelandeordernr() { return meddelandeordernr; }
    public void setMeddelandeordernr(Integer meddelandeordernr) { this.meddelandeordernr = meddelandeordernr; }

    public Fragekategori getFragekategori() { return fragekategori; }
    public void setFragekategori(Fragekategori fragekategori) { this.fragekategori = fragekategori; }

    public Integer getMottagarekund() { return mottagarekund; }
    public void setMottagarekund(Integer mottagarekund) { this.mottagarekund = mottagarekund; }

    public Integer getAvsandarekund() { return avsandarekund; }
    public void setAvsandarekund(Integer avsandarekund) { this.avsandarekund = avsandarekund; }

    public Integer getInsynsskyddat() { return insynsskyddat; }
    public void setInsynsskyddat(Integer insynsskyddat) { this.insynsskyddat = insynsskyddat; }

    public Integer getArkiverat() { return arkiverat; }
    public void setArkiverat(Integer arkiverat) { this.arkiverat = arkiverat; }

    public Integer getKanalinredd() { return kanalinredd; }
    public void setKanalinredd(Integer kanalinredd) { this.kanalinredd = kanalinredd; }

    public Integer getBilaga() { return bilaga; }
    public void setBilaga(Integer bilaga) { this.bilaga = bilaga; }

    public String getClearingrefid() { return clearingrefid; }
    public void setClearingrefid(String clearingrefid) { this.clearingrefid = clearingrefid; }

    public LocalDateTime getVisastill() { return visastill; }
    public void setVisastill(LocalDateTime visastill) { this.visastill = visastill; }

    public String getIsocountrycode() { return isocountrycode; }
    public void setIsocountrycode(String isocountrycode) { this.isocountrycode = isocountrycode; }

    public String getAssgmid() { return assgmid; }
    public void setAssgmid(String assgmid) { this.assgmid = assgmid; }

    public Integer getRubrikNr() { return rubrikNr; }
    public void setRubrikNr(Integer rubrikNr) { this.rubrikNr = rubrikNr; }

    public Integer getMeddelandNr() { return meddelandNr; }
    public void setMeddelandNr(Integer meddelandNr) { this.meddelandNr = meddelandNr; }

    public String getMediatyp() { return mediatyp; }
    public void setMediatyp(String mediatyp) { this.mediatyp = mediatyp; }

    public Integer getLas() { return las; }
    public void setLas(Integer las) { this.las = las; }

    public String getKategori() { return kategori; }
    public void setKategori(String kategori) { this.kategori = kategori; }

    public String getKontor() { return kontor; }
    public void setKontor(String kontor) { this.kontor = kontor; }

    public String getKundnamn() { return kundnamn; }
    public void setKundnamn(String kundnamn) { this.kundnamn = kundnamn; }

    public Meddelandetext getMeddelandetext() { return meddelandetext; }
    public void setMeddelandetext(Meddelandetext meddelandetext) { this.meddelandetext = meddelandetext; }
}
