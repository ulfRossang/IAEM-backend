package se.handelsbanken.iaem.entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "fragekategori", schema = "epatraktor")
public class Fragekategori implements Serializable {

    @Id
    @Column(name = "meddfrågekategori", precision = 2)
    private Integer meddfrågekategori;

    @Column(name = "kategoritext", length = 80)
    private String kategoritext;

    public Integer getMeddfrågekategori() { return meddfrågekategori; }
    public void setMeddfrågekategori(Integer meddfrågekategori) { this.meddfrågekategori = meddfrågekategori; }

    public String getKategoritext() { return kategoritext; }
    public void setKategoritext(String kategoritext) { this.kategoritext = kategoritext; }
}
