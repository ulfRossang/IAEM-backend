package se.handelsbanken.iaem.model;

public class Kanaler {
    public boolean papper;
    public boolean internet;

    public Kanaler() {}

    public Kanaler(boolean papper, boolean internet) {
        this.papper = papper;
        this.internet = internet;
    }
}
