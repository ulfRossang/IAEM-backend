package se.handelsbanken.iaem.model;

public class MassutskickItem {
    public String meddId;
    public String land;
    public String avsandare;
    public String amne;
    public String utskicksdatum;
    public String notifieringskategori;
    public String meddelande;
    public String status;   // "Under upplägg", "Klarmarkerad", "Skickad"

    public MassutskickItem() {}

    public MassutskickItem(String meddId, String land, String avsandare, String amne,
                            String utskicksdatum, String notifieringskategori,
                            String meddelande, String status) {
        this.meddId = meddId;
        this.land = land;
        this.avsandare = avsandare;
        this.amne = amne;
        this.utskicksdatum = utskicksdatum;
        this.notifieringskategori = notifieringskategori;
        this.meddelande = meddelande;
        this.status = status;
    }
}
