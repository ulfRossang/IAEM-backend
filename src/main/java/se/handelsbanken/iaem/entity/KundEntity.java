package se.handelsbanken.iaem.entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "kund", schema = "epatraktor")
public class KundEntity implements Serializable {

    @Id
    @Column(name = "kundnr", length = 32)
    private String kundnr;

    @Column(name = "kundnamn", length = 200)
    private String kundnamn;

    @Column(name = "land", length = 100)
    private String land;

    public String getKundnr() { return kundnr; }
    public void setKundnr(String kundnr) { this.kundnr = kundnr; }

    public String getKundnamn() { return kundnamn; }
    public void setKundnamn(String kundnamn) { this.kundnamn = kundnamn; }

    public String getLand() { return land; }
    public void setLand(String land) { this.land = land; }
}
