package se.handelsbanken.iaem.model;

public class Dokument {
    public String dokumentnamn;
    public String forbindelse;
    public String dokumentdatum;
    public String utskicksdatum;
    public String skickatsTill;   // "Digitalt" or "Papper"
    public String visasTill;
    public boolean last;
    public boolean borttaget;
    public boolean arkiverat;

    public Dokument() {}
}
