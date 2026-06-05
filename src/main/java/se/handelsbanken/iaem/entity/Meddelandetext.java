package se.handelsbanken.iaem.entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "meddelandetext", schema = "epatraktor")
public class Meddelandetext implements Serializable {

    @Id
    @Column(name = "persmeddid", length = 32)
    private String persmeddid;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "persmeddid")
    private MeddelandeHeader header;

    @Column(name = "brodtext", length = 23000)
    private String brodtext;

    @Column(name = "signeringsfil", length = 32)
    private String signeringsfil;

    public String getPersmeddid() { return persmeddid; }
    public void setPersmeddid(String persmeddid) { this.persmeddid = persmeddid; }

    public MeddelandeHeader getHeader() { return header; }
    public void setHeader(MeddelandeHeader header) { this.header = header; }

    public String getBrodtext() { return brodtext; }
    public void setBrodtext(String brodtext) { this.brodtext = brodtext; }

    public String getSigneringsfil() { return signeringsfil; }
    public void setSigneringsfil(String signeringsfil) { this.signeringsfil = signeringsfil; }
}
