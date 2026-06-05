package se.handelsbanken.iaem.model;

public class PubliceringJobb {
    public String jobbId;
    public String systembeteckning;
    public String informationsId;
    public String leveranstidpunkt;
    public String status;   // "Väntar", "Godkänd", "Nekad"

    public PubliceringJobb() {}

    public PubliceringJobb(String jobbId, String systembeteckning, String informationsId,
                           String leveranstidpunkt, String status) {
        this.jobbId = jobbId;
        this.systembeteckning = systembeteckning;
        this.informationsId = informationsId;
        this.leveranstidpunkt = leveranstidpunkt;
        this.status = status;
    }
}
