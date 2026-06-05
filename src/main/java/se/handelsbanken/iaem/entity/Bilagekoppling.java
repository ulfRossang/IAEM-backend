package se.handelsbanken.iaem.entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "bilagekoppling", schema = "epatraktor")
@IdClass(BilagekopplingId.class)
public class Bilagekoppling implements Serializable {

    @Id
    @Column(name = "persmeddid", length = 32)
    private String persmeddid;

    @Id
    @Column(name = "bilaga", length = 32)
    private String bilaga;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "persmeddid", insertable = false, updatable = false)
    private MeddelandeHeader header;

    public String getPersmeddid() { return persmeddid; }
    public void setPersmeddid(String persmeddid) { this.persmeddid = persmeddid; }

    public String getBilaga() { return bilaga; }
    public void setBilaga(String bilaga) { this.bilaga = bilaga; }

    public MeddelandeHeader getHeader() { return header; }
    public void setHeader(MeddelandeHeader header) { this.header = header; }
}
