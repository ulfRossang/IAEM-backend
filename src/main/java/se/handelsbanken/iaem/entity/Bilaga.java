package se.handelsbanken.iaem.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "bilaga", schema = "epatraktor")
public class Bilaga implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bilaga_id")
    private Long bilagaId;

    @Column(name = "meddelandeid", length = 32)
    private String meddelandeid;

    @Column(name = "bilaga_ref", length = 32)
    private String bilagaRef;

    @Column(name = "filnamn", length = 500)
    private String filnamn;

    @Column(name = "mimetype", length = 256)
    private String mimetype;

    @Column(name = "signeringshash", length = 40)
    private String signeringshash;

    @Column(name = "virusscanstatus", length = 32)
    private String virusscanstatus;

    @Column(name = "filstorlek")
    private Integer filstorlek;

    @Lob
    @Column(name = "fil")
    private byte[] fil;

    @Column(name = "uppladdningsdatum")
    private LocalDateTime uppladdningsdatum;

    public Long getBilagaId() { return bilagaId; }
    public void setBilagaId(Long bilagaId) { this.bilagaId = bilagaId; }

    public String getMeddelandeid() { return meddelandeid; }
    public void setMeddelandeid(String meddelandeid) { this.meddelandeid = meddelandeid; }

    public String getBilagaRef() { return bilagaRef; }
    public void setBilagaRef(String bilagaRef) { this.bilagaRef = bilagaRef; }

    public String getFilnamn() { return filnamn; }
    public void setFilnamn(String filnamn) { this.filnamn = filnamn; }

    public String getMimetype() { return mimetype; }
    public void setMimetype(String mimetype) { this.mimetype = mimetype; }

    public String getSigneringshash() { return signeringshash; }
    public void setSigneringshash(String signeringshash) { this.signeringshash = signeringshash; }

    public String getVirusscanstatus() { return virusscanstatus; }
    public void setVirusscanstatus(String virusscanstatus) { this.virusscanstatus = virusscanstatus; }

    public Integer getFilstorlek() { return filstorlek; }
    public void setFilstorlek(Integer filstorlek) { this.filstorlek = filstorlek; }

    public byte[] getFil() { return fil; }
    public void setFil(byte[] fil) { this.fil = fil; }

    public LocalDateTime getUppladdningsdatum() { return uppladdningsdatum; }
    public void setUppladdningsdatum(LocalDateTime uppladdningsdatum) { this.uppladdningsdatum = uppladdningsdatum; }
}
