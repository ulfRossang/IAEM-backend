package se.handelsbanken.iaem.model;

public class UtskickInstallning {
    public String kategori;
    public String avser;
    public String forbindelse;
    public boolean papper;
    public boolean internet;

    public UtskickInstallning() {}

    public UtskickInstallning(String kategori, String avser, String forbindelse,
                               boolean papper, boolean internet) {
        this.kategori = kategori;
        this.avser = avser;
        this.forbindelse = forbindelse;
        this.papper = papper;
        this.internet = internet;
    }
}
