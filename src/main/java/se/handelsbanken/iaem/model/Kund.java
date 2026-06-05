package se.handelsbanken.iaem.model;

public class Kund {
    public String kundnr;
    public String kundnamn;
    public String land;

    public Kund() {}

    public Kund(String kundnr, String kundnamn, String land) {
        this.kundnr = kundnr;
        this.kundnamn = kundnamn;
        this.land = land;
    }
}
