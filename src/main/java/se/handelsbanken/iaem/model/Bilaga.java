package se.handelsbanken.iaem.model;

public class Bilaga {
    public String filnamn;
    public int storlek;
    public String url;

    public Bilaga() {}

    public Bilaga(String filnamn, int storlek, String url) {
        this.filnamn = filnamn;
        this.storlek = storlek;
        this.url = url;
    }
}
