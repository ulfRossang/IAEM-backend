package se.handelsbanken.iaem.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import se.handelsbanken.iaem.entity.*;

import java.time.LocalDateTime;
import java.util.logging.Logger;

@ApplicationScoped
public class DatabaseSeeder {

    private static final Logger log = Logger.getLogger(DatabaseSeeder.class.getName());

    @PersistenceContext(unitName = "epatraktorPU")
    private EntityManager em;

    @Transactional
    public void onStart(@Observes @Initialized(ApplicationScoped.class) Object event) {
        try {
            Number count = (Number) em.createNativeQuery(
                    "SELECT COUNT(*) FROM epatraktor.kund").getSingleResult();
            if (count.longValue() > 0) {
                log.info("DatabaseSeeder: data already present, skipping seed.");
                return;
            }
        } catch (Exception e) {
            log.warning("DatabaseSeeder: could not check kund table: " + e.getMessage());
            return;
        }

        log.info("DatabaseSeeder: seeding mock data...");

        seedKunder();
        seedMeddelanden();
        seedUtskick();
        seedDokument();
        seedKuvert();
        seedUtskickInstallningar();
        seedPublicering();
        seedInformationsprodukter();
        seedDebiteringsuppgifter();
        seedMassutskick();

        log.info("DatabaseSeeder: seeding complete.");
    }

    private void seedKunder() {
        persist(kund("KD-71042", "Kund #71042", "Sverige"));
        persist(kund("KD-38815", "Kund #38815", "Sverige"));
        persist(kund("KD-90423", "Kund #90423", "Sverige"));
    }

    private void seedMeddelanden() {
        // MSG-2024-0041 for KD-71042
        MeddelandeHeader h1 = meddelandeHeader(
                "MSG-2024-0041", "KD-71042", "Kund #71042",
                "Testenv 7", "Kund #71042",
                LocalDateTime.parse("2025-01-22T09:58:00"),
                "Digital", "Kontoutdrag februari", true, false, true, "SE-6292");
        Meddelandetext t1 = meddelandetext(h1, "Ditt kontoutdrag för februari finns tillgängligt i din e-brevlåda.");
        h1.setMeddelandetext(t1);
        em.persist(h1);
        persistBilaga("MSG-2024-0041", "kontoutdrag-feb.pdf", 15360);

        // MSG-2024-0039 for KD-71042
        MeddelandeHeader h2 = meddelandeHeader(
                "MSG-2024-0039", "KD-71042", "Kund #71042",
                "Handelsbanken", "Kund #71042",
                LocalDateTime.parse("2025-02-10T14:20:00"),
                "Kontoutdrag", "Kontoutdrag januari", false, false, false, "SE-6292");
        Meddelandetext t2 = meddelandetext(h2, "Ditt kontoutdrag för januari finns tillgängligt.");
        h2.setMeddelandetext(t2);
        em.persist(h2);
        persistBilaga("MSG-2024-0039", "kontoutdrag-jan.pdf", 14080);

        // MSG-2024-0038 for KD-38815
        MeddelandeHeader h3 = meddelandeHeader(
                "MSG-2024-0038", "KD-38815", "Kund #38815",
                "Handelsbanken", "Kund #38815",
                LocalDateTime.parse("2025-02-10T14:20:00"),
                "Kontoutdrag", "Kontoutdrag januari", false, false, false, "SE-6292");
        Meddelandetext t3 = meddelandetext(h3, "Ditt kontoutdrag för januari finns tillgängligt.");
        h3.setMeddelandetext(t3);
        em.persist(h3);

        // MSG-2024-0035 for KD-90423
        MeddelandeHeader h4 = meddelandeHeader(
                "MSG-2024-0035", "KD-90423", "Kund #90423",
                "Handelsbanken", "Kund #90423",
                LocalDateTime.parse("2025-03-01T08:10:00"),
                "Avtal", "Nytt kortavtal", true, false, false, "SE-6292");
        Meddelandetext t4 = meddelandetext(h4, "Bekräftelse på ditt nya kortavtal bifogas.");
        h4.setMeddelandetext(t4);
        em.persist(h4);
        persistBilaga("MSG-2024-0035", "kortavtal.pdf", 20480);
    }

    private void seedUtskick() {
        UtskickEntity u1 = utskickEntity("UTK-2025-0001", "KD-71042", "Kund #71042",
                "Handelsbanken", "Kund #71042", "2025-11-19T16:35:00",
                "Avräkningsnota", "Avräkningsnota nov", true, false, true, "2036-03-20",
                "Din fondorder är genomförd enligt bifogad avräkningsnota.\n\nMed vänlig hälsning\nHandelsbanken");
        em.persist(u1);
        persistUtskickBilaga(u1, "Avräkningsnota nov.pdf", "/api/v1/files/avrakningsnota-nov.pdf");

        UtskickEntity u2 = utskickEntity("UTK-2025-0002", "KD-71042", "Kund #71042",
                "Handelsbanken", "Kund #71042", "2025-11-10T15:40:00",
                "Kontoutdrag", "Kontoutdrag okt", true, false, false, "2036-03-20",
                "Ditt kontoutdrag för oktober 2025 finns tillgängligt.");
        em.persist(u2);
        persistUtskickBilaga(u2, "Kontoutdrag okt.pdf", "/api/v1/files/kontoutdrag-okt.pdf");

        UtskickEntity u3 = utskickEntity("UTK-2025-0003", "KD-71042", "Kund #71042",
                "Handelsbanken", "Kund #71042", "2025-11-04T11:22:00",
                "Avräkningsnota", "Avräkningsnota okt", false, false, false, "2036-03-20",
                "Din fondorder är genomförd enligt bifogad avräkningsnota.");
        em.persist(u3);
        persistUtskickBilaga(u3, "Avräkningsnota okt.pdf", "/api/v1/files/avrakningsnota-okt.pdf");

        UtskickEntity u4 = utskickEntity("UTK-2025-0004", "KD-71042", "Kund #71042",
                "Handelsbanken", "Kund #71042", "2025-11-03T12:44:00",
                "Bokföringsavi", "Bokföringsavi nov", false, false, false, "2036-03-20",
                "Bokföringsavi för november bifogad.");
        em.persist(u4);
        persistUtskickBilaga(u4, "Bokföringsavi nov.pdf", "/api/v1/files/bokforingsavi-nov.pdf");

        UtskickEntity u5 = utskickEntity("UTK-2025-0005", "KD-71042", "Kund #71042",
                "Handelsbanken", "Kund #71042", "2025-08-18T10:56:00",
                "Avtal", "IGDH Test doktyp 2", true, false, true, "2036-03-20",
                "Avtalsbekräftelse bifogad.");
        em.persist(u5);
        persistUtskickBilaga(u5, "IGDH Test doktyp 2.pdf", "/api/v1/files/igdh-test.pdf");
    }

    private void seedDokument() {
        DokumentEntity d1 = new DokumentEntity();
        d1.setKundnr("KD-71042");
        d1.setDokumentnamn("Avtal Allkonto");
        d1.setForbindelse("");
        d1.setDokumentdatum("2025-08-13");
        d1.setUtskicksdatum("2025-08-13");
        d1.setSkickatsTill("Digitalt");
        d1.setVisasTill("2027-02-13");
        d1.setLast(1);
        d1.setBorttaget(0);
        d1.setArkiverat(0);
        em.persist(d1);

        DokumentEntity d2 = new DokumentEntity();
        d2.setKundnr("KD-71042");
        d2.setDokumentnamn("Kontoutdrag sep");
        d2.setForbindelse("SE-001");
        d2.setDokumentdatum("2025-09-30");
        d2.setUtskicksdatum("2025-10-01");
        d2.setSkickatsTill("Digitalt");
        d2.setVisasTill("2027-10-01");
        d2.setLast(1);
        d2.setBorttaget(0);
        d2.setArkiverat(1);
        em.persist(d2);
    }

    private void seedKuvert() {
        KuvertEntity k = new KuvertEntity();
        k.setKuvertId("ENV-2024-88441");
        k.setKundnr("KD-71042");
        k.setKundnamn("Kund #71042");
        k.setDatum("2024-02-29");
        k.setMall("Kontoutdrag");
        k.setKanal("Digital brevlåda");
        k.setStatus("Levererat");
        em.persist(k);
    }

    private void seedUtskickInstallningar() {
        String kundnr = "KD-71042";
        persistInstallning(kundnr, "Kontoutdrag",    "Konto",            "SE-001-001",  false, false);
        persistInstallning(kundnr, "Bokföringsavi",  "Konto",            "SE-011-011",  true,  false);
        persistInstallning(kundnr, "Bokföringsavi",  "Konto",            "SE-001-002",  false, false);
        persistInstallning(kundnr, "Låneavi",        "Lån",              "0",           false, false);
        persistInstallning(kundnr, "Avräkningsnota", "Värdepapper",      "0",           true,  false);
        persistInstallning(kundnr, "Lön/pension",    "Handelsbanken",    "",            false, false);
        persistInstallning(kundnr, "Årsbesked",      "Depå",             "0",           false, false);
        persistInstallning(kundnr, "Årsbesked",      "Fonder",           "",            false, false);
        persistInstallning(kundnr, "Avtal",          "Lån Stadshypotek", "SE-005-05",   true,  false);
    }

    private void seedPublicering() {
        InformationssambandEntity s = new InformationssambandEntity();
        s.setId("SAM-001");
        s.setSystembeteckning("EPOX");
        s.setInformationsId("Q294902");
        s.setPubliceraAutomatiskt(0);
        em.persist(s);

        PubliceringJobbEntity j1 = new PubliceringJobbEntity();
        j1.setJobbId("EPOX-JOB-001");
        j1.setSystembeteckning("EPOX");
        j1.setInformationsId("Q294902");
        j1.setLeveranstidpunkt(LocalDateTime.parse("2026-06-01T08:00:00"));
        j1.setStatus("Väntar");
        em.persist(j1);

        PubliceringJobbEntity j2 = new PubliceringJobbEntity();
        j2.setJobbId("REVL-JOB-002");
        j2.setSystembeteckning("REVL");
        j2.setInformationsId("Q295100");
        j2.setLeveranstidpunkt(LocalDateTime.parse("2026-06-02T08:00:00"));
        j2.setStatus("Väntar");
        em.persist(j2);
    }

    private void seedInformationsprodukter() {
        persistProdukt("32225", "Konto, Account", "Sverige", "Aktiv",
                "NOT-01 Räntebesked", "INLÅ", false,
                false, true, true, true, false, true, "222", "223", 120, 132, "");

        persistProdukt("32228", "Konto", "Sverige", "Aktiv",
                "NOT-01 Räntebesked", "INLÅ", false,
                false, true, true, true, false, true, "222", "223", 120, 132, "");

        persistProdukt("32230", "Fondkonto", "Sverige", "Aktiv",
                "NOT-02 Kortbekräftelse", "FOND", false,
                false, true, true, true, false, true, "224", "225", 120, 132, "");

        persistProdukt("32241", "Bolån", "Sverige", "Aktiv",
                "NOT-01 Räntebesked", "HBOS", false,
                false, true, true, true, false, true, "226", "227", 120, 132, "");
    }

    private void seedDebiteringsuppgifter() {
        persistDebitering("DEB-001", "-", "INLÅ", "217805", "217905", "68821");
        persistDebitering("DEB-002", "-", "INLÅ", "217826", "217926", "68821");
        persistDebitering("DEB-003", "-", "REVL", "217807", "217907", "69314");
        persistDebitering("DEB-004", "-", "HBOS", "217804", "217904", "60180");
        persistDebitering("DEB-005", "-", "AKKO", "217800", "217900", "60280");
    }

    private void seedMassutskick() {
        persistMassutskick("250", "Sverige", "Handelsbanken", "TBD",
                "2026-06-25", "", "", "Under upplägg");
        persistMassutskick("214", "Sverige", "Handelsbanken", "Byte av försäkringssystem",
                "2024-09-06", "", "Brev om byte av försäkringssystem.", "Klarmarkerad");
        persistMassutskick("213", "Sverige", "Handelsbanken", "Förfall av dokumentation för direktnedsättning",
                "2024-09-02", "", "Information om förfall av dokumentation.", "Klarmarkerad");
        persistMassutskick("211", "Sverige", "Handelsbanken", "Fel i utskick från Handelsbanken",
                "2024-06-26", "", "Rättelse av tidigare utskick.", "Klarmarkerad");
        persistMassutskick("210", "Sverige", "Handelsbanken", "Difference in tax purposes",
                "2024-06-03", "", "Information regarding tax differences.", "Klarmarkerad");
        persistMassutskick("209", "Sverige", "Handelsbanken", "e-Kapitalkonto blir Sparkonto",
                "2024-05-02", "", "Information om namnbyte av kontotyp.", "Klarmarkerad");
    }

    // ---- Helper factory methods ----

    private KundEntity kund(String kundnr, String kundnamn, String land) {
        KundEntity k = new KundEntity();
        k.setKundnr(kundnr);
        k.setKundnamn(kundnamn);
        k.setLand(land);
        return k;
    }

    private MeddelandeHeader meddelandeHeader(String id, String kundnr, String kundnamn,
            String avsandare, String mottagare, LocalDateTime datum,
            String kategori, String rubrik, boolean las, boolean borttaget,
            boolean arkiverat, String kontor) {
        MeddelandeHeader h = new MeddelandeHeader();
        h.setPersmeddid(id);
        h.setKundnummer(kundnr);
        h.setKundnamn(kundnamn);
        h.setAvsandare(avsandare);
        h.setMottagare(mottagare);
        h.setSkapdatum(datum);
        h.setKategori(kategori);
        h.setRubrik(rubrik);
        h.setLas(las ? 1 : 0);
        h.setBorttagetvmot(borttaget ? 1 : 0);
        h.setArkiverat(arkiverat ? 1 : 0);
        h.setKontor(kontor);
        return h;
    }

    private Meddelandetext meddelandetext(MeddelandeHeader header, String brodtext) {
        Meddelandetext t = new Meddelandetext();
        t.setHeader(header);
        t.setBrodtext(brodtext);
        return t;
    }

    private void persistBilaga(String meddId, String filnamn, int storlek) {
        Bilaga b = new Bilaga();
        b.setMeddelandeid(meddId);
        b.setFilnamn(filnamn);
        b.setFilstorlek(storlek);
        b.setUppladdningsdatum(LocalDateTime.now());
        em.persist(b);
    }

    private UtskickEntity utskickEntity(String id, String kundnr, String kundnamn,
            String avsandare, String mottagare, String datum,
            String kategori, String amne, boolean las, boolean borttaget,
            boolean arkiverat, String visasTill, String innehall) {
        UtskickEntity u = new UtskickEntity();
        u.setUtskickId(id);
        u.setKundnr(kundnr);
        u.setKundnamn(kundnamn);
        u.setAvsandare(avsandare);
        u.setMottagare(mottagare);
        u.setDatum(LocalDateTime.parse(datum));
        u.setKategori(kategori);
        u.setAmne(amne);
        u.setLas(las ? 1 : 0);
        u.setBorttaget(borttaget ? 1 : 0);
        u.setArkiverat(arkiverat ? 1 : 0);
        u.setVisasTill(visasTill);
        u.setInnehall(innehall);
        return u;
    }

    private void persistUtskickBilaga(UtskickEntity utskick, String filnamn, String url) {
        UtskickBilagaEntity b = new UtskickBilagaEntity();
        b.setUtskick(utskick);
        b.setFilnamn(filnamn);
        b.setUrl(url);
        em.persist(b);
    }

    private void persistInstallning(String kundnr, String kategori, String avser,
            String forbindelse, boolean papper, boolean internet) {
        UtskickInstallningEntity e = new UtskickInstallningEntity();
        e.setKundnr(kundnr);
        e.setKategori(kategori);
        e.setAvser(avser);
        e.setForbindelse(forbindelse);
        e.setPapper(papper ? 1 : 0);
        e.setInternet(internet ? 1 : 0);
        em.persist(e);
    }

    private void persistProdukt(String id, String namn, String land, String status,
            String notifieringskategori, String systembeteckning, boolean insynsskyddad,
            boolean defaultPapper, boolean defaultInternet,
            boolean tilllåtnaPapper, boolean tilllåtnaInternet,
            boolean obligatoriskaPapper, boolean obligatoriskaInternet,
            String avgiftsidPapper, String avgiftsidInternet,
            int visningstidEArkivMan, int lagringstidDiskMan, String meddelandetext) {
        InformationsproduktEntity p = new InformationsproduktEntity();
        p.setId(id);
        p.setNamn(namn);
        p.setLand(land);
        p.setStatus(status);
        p.setNotifieringskategori(notifieringskategori);
        p.setSystembeteckning(systembeteckning);
        p.setInsynsskyddad(insynsskyddad ? 1 : 0);
        p.setDefaultPapper(defaultPapper ? 1 : 0);
        p.setDefaultInternet(defaultInternet ? 1 : 0);
        p.setTilllåtnaPapper(tilllåtnaPapper ? 1 : 0);
        p.setTilllåtnaInternet(tilllåtnaInternet ? 1 : 0);
        p.setObligatoriskaPapper(obligatoriskaPapper ? 1 : 0);
        p.setObligatoriskaInternet(obligatoriskaInternet ? 1 : 0);
        p.setAvgiftsidPapper(avgiftsidPapper);
        p.setAvgiftsidInternet(avgiftsidInternet);
        p.setVisningstidEArkivMan(visningstidEArkivMan);
        p.setLagringstidDiskMan(lagringstidDiskMan);
        p.setMeddelandetext(meddelandetext);
        em.persist(p);
    }

    private void persistDebitering(String produktid, String meddelandeid, String systembeteckning,
            String antsKodInternet, String antsKodEjInternet, String resultatstalle) {
        DebiteringsuppgiftEntity d = new DebiteringsuppgiftEntity();
        d.setProduktid(produktid);
        d.setMeddelandeid(meddelandeid);
        d.setSystembeteckning(systembeteckning);
        d.setAntsKodInternet(antsKodInternet);
        d.setAntsKodEjInternet(antsKodEjInternet);
        d.setResultatstalle(resultatstalle);
        em.persist(d);
    }

    private void persistMassutskick(String meddId, String land, String avsandare, String amne,
            String utskicksdatum, String notifieringskategori, String meddelande, String status) {
        MassutskickEntity m = new MassutskickEntity();
        m.setMeddId(meddId);
        m.setLand(land);
        m.setAvsandare(avsandare);
        m.setAmne(amne);
        m.setUtskicksdatum(utskicksdatum);
        m.setNotifieringskategori(notifieringskategori);
        m.setMeddelande(meddelande);
        m.setStatus(status);
        em.persist(m);
    }

    private void persist(Object entity) {
        em.persist(entity);
    }
}
