package se.handelsbanken.iaem.model;

public class Kuvert {
    public String kuvertId;
    public String kundnr;
    public String kundnamn;
    public String datum;
    public String mall;
    public String kanal;
    public String status;    // "Levererat", "Ej levererat", "Returnerat"

    public Kuvert() {}

    public Kuvert(String kuvertId, String kundnr, String kundnamn, String datum,
                  String mall, String kanal, String status) {
        this.kuvertId = kuvertId;
        this.kundnr = kundnr;
        this.kundnamn = kundnamn;
        this.datum = datum;
        this.mall = mall;
        this.kanal = kanal;
        this.status = status;
    }
}
