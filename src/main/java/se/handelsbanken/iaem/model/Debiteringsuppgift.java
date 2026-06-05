package se.handelsbanken.iaem.model;

public class Debiteringsuppgift {
    public String produktid;
    public String meddelandeid;
    public String systembeteckning;
    public String antsKodInternet;
    public String antsKodEjInternet;
    public String resultatstalle;

    public Debiteringsuppgift() {}

    public Debiteringsuppgift(String produktid, String meddelandeid, String systembeteckning,
                               String antsKodInternet, String antsKodEjInternet, String resultatstalle) {
        this.produktid = produktid;
        this.meddelandeid = meddelandeid;
        this.systembeteckning = systembeteckning;
        this.antsKodInternet = antsKodInternet;
        this.antsKodEjInternet = antsKodEjInternet;
        this.resultatstalle = resultatstalle;
    }
}
