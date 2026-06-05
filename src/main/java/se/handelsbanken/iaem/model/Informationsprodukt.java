package se.handelsbanken.iaem.model;

public class Informationsprodukt {
    public String id;
    public String namn;
    public String land;
    public String status;
    public String notifieringskategori;
    public String systembeteckning;
    public boolean insynsskyddad;
    public Kanaler defaultkanaler;
    public Kanaler tillåtnaKanaler;
    public Kanaler obligatoriskaKanaler;
    public String avgiftsidPapper;
    public String avgiftsidInternet;
    public int visningstidEArkivMan;
    public int lagringstidDiskMan;
    public String meddelandetext;

    public Informationsprodukt() {}
}
