package se.handelsbanken.iaem.model;

public class Meddelande {
    public String id;
    public String kundnr;
    public String kundnamn;
    public String avsandare;
    public String mottagare;
    public String datum;       // ISO date-time string
    public String kategori;
    public String amne;
    public boolean las;
    public boolean borttaget;
    public boolean arkiverat;
    public String status;
    public String kontor;

    public Meddelande() {}
}
