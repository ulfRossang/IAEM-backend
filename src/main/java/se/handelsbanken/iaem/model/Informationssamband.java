package se.handelsbanken.iaem.model;

public class Informationssamband {
    public String id;
    public String systembeteckning;
    public String informationsId;
    public boolean publiceraAutomatiskt;

    public Informationssamband() {}

    public Informationssamband(String id, String systembeteckning,
                                String informationsId, boolean publiceraAutomatiskt) {
        this.id = id;
        this.systembeteckning = systembeteckning;
        this.informationsId = informationsId;
        this.publiceraAutomatiskt = publiceraAutomatiskt;
    }
}
