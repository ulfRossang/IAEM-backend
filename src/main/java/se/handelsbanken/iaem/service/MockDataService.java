package se.handelsbanken.iaem.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import se.handelsbanken.iaem.entity.*;
import se.handelsbanken.iaem.model.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class MockDataService {

    @PersistenceContext(unitName = "epatraktorPU")
    private EntityManager em;

    // =========================================================
    // Kunder
    // =========================================================

    public Optional<Kund> findKund(String kundnr) {
        KundEntity entity = em.find(KundEntity.class, kundnr);
        if (entity == null) return Optional.empty();
        return Optional.of(toKund(entity));
    }

    // =========================================================
    // Meddelanden
    // =========================================================

    @SuppressWarnings("unchecked")
    public List<MeddelandeDetail> getMeddelanden(String kundnr) {
        List<MeddelandeHeader> headers = em.createQuery(
                "SELECT m FROM MeddelandeHeader m WHERE m.kundnummer = :kundnr ORDER BY m.skapdatum DESC",
                MeddelandeHeader.class)
            .setParameter("kundnr", kundnr)
            .getResultList();
        List<MeddelandeDetail> result = new ArrayList<>();
        for (MeddelandeHeader h : headers) {
            result.add(toMeddelandeDetail(h));
        }
        return result;
    }

    public Optional<MeddelandeDetail> getMeddelande(String kundnr, String meddId) {
        List<MeddelandeHeader> list = em.createQuery(
                "SELECT m FROM MeddelandeHeader m WHERE m.kundnummer = :kundnr AND m.persmeddid = :id",
                MeddelandeHeader.class)
            .setParameter("kundnr", kundnr)
            .setParameter("id", meddId)
            .getResultList();
        if (list.isEmpty()) return Optional.empty();
        return Optional.of(toMeddelandeDetail(list.get(0)));
    }

    // =========================================================
    // Utskick
    // =========================================================

    public List<UtskickDetail> getUtskick(String kundnr) {
        List<UtskickEntity> entities = em.createQuery(
                "SELECT u FROM UtskickEntity u WHERE u.kundnr = :kundnr ORDER BY u.datum DESC",
                UtskickEntity.class)
            .setParameter("kundnr", kundnr)
            .getResultList();
        List<UtskickDetail> result = new ArrayList<>();
        for (UtskickEntity e : entities) {
            result.add(toUtskickDetail(e));
        }
        return result;
    }

    public Optional<UtskickDetail> getUtskickById(String kundnr, String utskickId) {
        List<UtskickEntity> list = em.createQuery(
                "SELECT u FROM UtskickEntity u WHERE u.kundnr = :kundnr AND u.utskickId = :id",
                UtskickEntity.class)
            .setParameter("kundnr", kundnr)
            .setParameter("id", utskickId)
            .getResultList();
        if (list.isEmpty()) return Optional.empty();
        return Optional.of(toUtskickDetail(list.get(0)));
    }

    // =========================================================
    // Dokument
    // =========================================================

    public List<Dokument> getDokument(String kundnr) {
        List<DokumentEntity> entities = em.createQuery(
                "SELECT d FROM DokumentEntity d WHERE d.kundnr = :kundnr",
                DokumentEntity.class)
            .setParameter("kundnr", kundnr)
            .getResultList();
        List<Dokument> result = new ArrayList<>();
        for (DokumentEntity e : entities) {
            result.add(toDokument(e));
        }
        return result;
    }

    // =========================================================
    // Kuvert
    // =========================================================

    public Optional<Kuvert> findKuvert(String kuvertId) {
        KuvertEntity entity = em.find(KuvertEntity.class, kuvertId);
        if (entity == null) return Optional.empty();
        return Optional.of(toKuvert(entity));
    }

    // =========================================================
    // UtskickInstallningar
    // =========================================================

    public List<UtskickInstallning> getUtskickInstallningar(String kundnr) {
        List<UtskickInstallningEntity> entities = em.createQuery(
                "SELECT u FROM UtskickInstallningEntity u WHERE u.kundnr = :kundnr",
                UtskickInstallningEntity.class)
            .setParameter("kundnr", kundnr)
            .getResultList();
        List<UtskickInstallning> result = new ArrayList<>();
        for (UtskickInstallningEntity e : entities) {
            result.add(toUtskickInstallning(e));
        }
        return result;
    }

    @Transactional
    public void saveUtskickInstallningar(String kundnr, List<UtskickInstallning> settings) {
        em.createQuery("DELETE FROM UtskickInstallningEntity u WHERE u.kundnr = :kundnr")
            .setParameter("kundnr", kundnr)
            .executeUpdate();
        for (UtskickInstallning s : settings) {
            UtskickInstallningEntity e = new UtskickInstallningEntity();
            e.setKundnr(kundnr);
            e.setKategori(s.kategori);
            e.setAvser(s.avser);
            e.setForbindelse(s.forbindelse);
            e.setPapper(s.papper ? 1 : 0);
            e.setInternet(s.internet ? 1 : 0);
            em.persist(e);
        }
    }

    // =========================================================
    // Publicering installningar
    // =========================================================

    public List<Informationssamband> getInformationssamband() {
        List<InformationssambandEntity> entities = em.createQuery(
                "SELECT s FROM InformationssambandEntity s",
                InformationssambandEntity.class)
            .getResultList();
        List<Informationssamband> result = new ArrayList<>();
        for (InformationssambandEntity e : entities) {
            result.add(toInformationssamband(e));
        }
        return result;
    }

    @Transactional
    public Informationssamband createInformationssamband(InformationssambandInput input) {
        // Generate next ID: SAM-NNN
        Number maxSeq = (Number) em.createNativeQuery(
                "SELECT COALESCE(MAX(CAST(SUBSTRING(id FROM 5) AS INTEGER)), 0) FROM epatraktor.informationssamband")
            .getSingleResult();
        String newId = "SAM-" + String.format("%03d", maxSeq.intValue() + 1);

        InformationssambandEntity e = new InformationssambandEntity();
        e.setId(newId);
        e.setSystembeteckning(input.systembeteckning);
        e.setInformationsId(input.informationsId);
        e.setPubliceraAutomatiskt(input.publiceraAutomatiskt ? 1 : 0);
        em.persist(e);
        return toInformationssamband(e);
    }

    @Transactional
    public Optional<Informationssamband> updateInformationssamband(String id, InformationssambandInput input) {
        InformationssambandEntity e = em.find(InformationssambandEntity.class, id);
        if (e == null) return Optional.empty();
        e.setSystembeteckning(input.systembeteckning);
        e.setInformationsId(input.informationsId);
        e.setPubliceraAutomatiskt(input.publiceraAutomatiskt ? 1 : 0);
        return Optional.of(toInformationssamband(e));
    }

    @Transactional
    public boolean deleteInformationssamband(String id) {
        InformationssambandEntity e = em.find(InformationssambandEntity.class, id);
        if (e == null) return false;
        em.remove(e);
        return true;
    }

    // =========================================================
    // Publicering jobb
    // =========================================================

    public List<PubliceringJobb> getPubliceringJobb() {
        List<PubliceringJobbEntity> entities = em.createQuery(
                "SELECT j FROM PubliceringJobbEntity j",
                PubliceringJobbEntity.class)
            .getResultList();
        List<PubliceringJobb> result = new ArrayList<>();
        for (PubliceringJobbEntity e : entities) {
            result.add(toPubliceringJobb(e));
        }
        return result;
    }

    @Transactional
    public boolean godkannJobb(String jobbId, boolean godkand) {
        PubliceringJobbEntity e = em.find(PubliceringJobbEntity.class, jobbId);
        if (e == null) return false;
        e.setStatus(godkand ? "Godkänd" : "Nekad");
        return true;
    }

    // =========================================================
    // Informationsprodukter
    // =========================================================

    public List<Informationsprodukt> getInformationsprodukter(String land) {
        List<InformationsproduktEntity> entities;
        if (land == null || land.isBlank()) {
            entities = em.createQuery(
                    "SELECT p FROM InformationsproduktEntity p",
                    InformationsproduktEntity.class)
                .getResultList();
        } else {
            entities = em.createQuery(
                    "SELECT p FROM InformationsproduktEntity p WHERE LOWER(p.land) = LOWER(:land)",
                    InformationsproduktEntity.class)
                .setParameter("land", land)
                .getResultList();
        }
        List<Informationsprodukt> result = new ArrayList<>();
        for (InformationsproduktEntity e : entities) {
            result.add(toInformationsprodukt(e));
        }
        return result;
    }

    @Transactional
    public Informationsprodukt createInformationsprodukt(InformationsproduktInput input) {
        Number maxId = (Number) em.createNativeQuery(
                "SELECT COALESCE(MAX(CAST(id AS INTEGER)), 33000) FROM epatraktor.informationsprodukt")
            .getSingleResult();
        String newId = String.valueOf(maxId.intValue() + 1);

        InformationsproduktEntity e = new InformationsproduktEntity();
        e.setId(newId);
        applyProduktInput(e, input);
        em.persist(e);
        return toInformationsprodukt(e);
    }

    @Transactional
    public Optional<Informationsprodukt> updateInformationsprodukt(String id, InformationsproduktInput input) {
        InformationsproduktEntity e = em.find(InformationsproduktEntity.class, id);
        if (e == null) return Optional.empty();
        applyProduktInput(e, input);
        return Optional.of(toInformationsprodukt(e));
    }

    // =========================================================
    // Debiteringsuppgifter
    // =========================================================

    public List<Debiteringsuppgift> getDebiteringsuppgifter() {
        List<DebiteringsuppgiftEntity> entities = em.createQuery(
                "SELECT d FROM DebiteringsuppgiftEntity d",
                DebiteringsuppgiftEntity.class)
            .getResultList();
        List<Debiteringsuppgift> result = new ArrayList<>();
        for (DebiteringsuppgiftEntity e : entities) {
            result.add(toDebiteringsuppgift(e));
        }
        return result;
    }

    @Transactional
    public Debiteringsuppgift createDebiteringsuppgift(DebiteringsuppgiftInput input) {
        DebiteringsuppgiftEntity e = new DebiteringsuppgiftEntity();
        e.setProduktid(input.produktid);
        e.setMeddelandeid(input.meddelandeid);
        e.setSystembeteckning(input.systembeteckning);
        e.setAntsKodInternet(input.antsKodInternet);
        e.setAntsKodEjInternet(input.antsKodEjInternet);
        e.setResultatstalle(input.resultatstalle);
        em.persist(e);
        return toDebiteringsuppgift(e);
    }

    @Transactional
    public Optional<Debiteringsuppgift> updateDebiteringsuppgift(String produktid, DebiteringsuppgiftInput input) {
        DebiteringsuppgiftEntity e = em.find(DebiteringsuppgiftEntity.class, produktid);
        if (e == null) return Optional.empty();
        e.setMeddelandeid(input.meddelandeid);
        e.setSystembeteckning(input.systembeteckning);
        e.setAntsKodInternet(input.antsKodInternet);
        e.setAntsKodEjInternet(input.antsKodEjInternet);
        e.setResultatstalle(input.resultatstalle);
        return Optional.of(toDebiteringsuppgift(e));
    }

    @Transactional
    public boolean deleteDebiteringsuppgift(String produktid) {
        DebiteringsuppgiftEntity e = em.find(DebiteringsuppgiftEntity.class, produktid);
        if (e == null) return false;
        em.remove(e);
        return true;
    }

    // =========================================================
    // Massutskick
    // =========================================================

    public List<MassutskickItem> getMassutskick() {
        List<MassutskickEntity> entities = em.createQuery(
                "SELECT m FROM MassutskickEntity m ORDER BY m.meddId DESC",
                MassutskickEntity.class)
            .getResultList();
        List<MassutskickItem> result = new ArrayList<>();
        for (MassutskickEntity e : entities) {
            result.add(toMassutskickItem(e));
        }
        return result;
    }

    @Transactional
    public MassutskickItem createMassutskick(MassutskickInput input) {
        Number maxId = (Number) em.createNativeQuery(
                "SELECT COALESCE(MAX(CAST(medd_id AS INTEGER)), 300) FROM epatraktor.massutskick")
            .getSingleResult();
        String newId = String.valueOf(maxId.intValue() + 1);

        MassutskickEntity e = new MassutskickEntity();
        e.setMeddId(newId);
        e.setLand(input.land);
        e.setAvsandare(input.avsandare);
        e.setAmne(input.amne);
        e.setUtskicksdatum(input.utskicksdatum);
        e.setNotifieringskategori(input.notifieringskategori);
        e.setMeddelande(input.meddelande);
        e.setStatus("Under upplägg");
        em.persist(e);
        return toMassutskickItem(e);
    }

    @Transactional
    public Optional<MassutskickItem> updateMassutskick(String meddId, MassutskickInput input) {
        MassutskickEntity e = em.find(MassutskickEntity.class, meddId);
        if (e == null) return Optional.empty();
        e.setLand(input.land);
        e.setAvsandare(input.avsandare);
        e.setAmne(input.amne);
        e.setUtskicksdatum(input.utskicksdatum);
        e.setNotifieringskategori(input.notifieringskategori);
        e.setMeddelande(input.meddelande);
        return Optional.of(toMassutskickItem(e));
    }

    @Transactional
    public boolean deleteMassutskick(String meddId) {
        MassutskickEntity e = em.find(MassutskickEntity.class, meddId);
        if (e == null) return false;
        em.remove(e);
        return true;
    }

    /**
     * Klarmarkera: only allowed when status is "Under upplägg".
     * Returns 0=not found, 1=success, 2=conflict.
     */
    @Transactional
    public int klarmarkeraMassutskick(String meddId) {
        MassutskickEntity e = em.find(MassutskickEntity.class, meddId);
        if (e == null) return 0;
        if ("Skickad".equals(e.getStatus()) || "Klarmarkerad".equals(e.getStatus())) {
            return 2;
        }
        e.setStatus("Klarmarkerad");
        return 1;
    }

    // =========================================================
    // Mapping helpers: entity -> model
    // =========================================================

    private Kund toKund(KundEntity e) {
        return new Kund(e.getKundnr(), e.getKundnamn(), e.getLand());
    }

    private MeddelandeDetail toMeddelandeDetail(MeddelandeHeader h) {
        MeddelandeDetail m = new MeddelandeDetail();
        m.id = h.getPersmeddid() != null ? h.getPersmeddid().trim() : null;
        m.kundnr = h.getKundnummer();
        m.kundnamn = h.getKundnamn();
        m.avsandare = h.getAvsandare();
        m.mottagare = h.getMottagare();
        m.datum = h.getSkapdatum() != null ? h.getSkapdatum().toString() : null;
        m.kategori = h.getKategori();
        m.amne = h.getRubrik();
        m.las = h.getLas() != null && h.getLas() == 1;
        m.borttaget = h.getBorttagetvmot() != null && h.getBorttagetvmot() == 1;
        m.arkiverat = h.getArkiverat() != null && h.getArkiverat() == 1;
        m.kontor = h.getKontor();
        m.status = "Skickat"; // default status

        // Load text
        Meddelandetext txt = h.getMeddelandetext();
        m.innehall = txt != null ? txt.getBrodtext() : "";

        // Load bilagor from bilaga table
        m.bilagor = loadBilagor(m.id);
        return m;
    }

    private List<se.handelsbanken.iaem.model.Bilaga> loadBilagor(String meddId) {
        if (meddId == null) return List.of();
        List<se.handelsbanken.iaem.entity.Bilaga> entities = em.createQuery(
                "SELECT b FROM Bilaga b WHERE b.meddelandeid = :meddId",
                se.handelsbanken.iaem.entity.Bilaga.class)
            .setParameter("meddId", meddId)
            .getResultList();
        List<se.handelsbanken.iaem.model.Bilaga> result = new ArrayList<>();
        for (se.handelsbanken.iaem.entity.Bilaga b : entities) {
            result.add(new se.handelsbanken.iaem.model.Bilaga(
                    b.getFilnamn(),
                    b.getFilstorlek() != null ? b.getFilstorlek() : 0,
                    "/api/v1/files/" + b.getFilnamn()));
        }
        return result;
    }

    private UtskickDetail toUtskickDetail(UtskickEntity e) {
        UtskickDetail u = new UtskickDetail();
        u.id = e.getUtskickId();
        u.kundnr = e.getKundnr();
        u.kundnamn = e.getKundnamn();
        u.avsandare = e.getAvsandare();
        u.mottagare = e.getMottagare();
        u.datum = e.getDatum() != null ? e.getDatum().toString() : null;
        u.kategori = e.getKategori();
        u.amne = e.getAmne();
        u.las = e.getLas() != null && e.getLas() == 1;
        u.borttaget = e.getBorttaget() != null && e.getBorttaget() == 1;
        u.arkiverat = e.getArkiverat() != null && e.getArkiverat() == 1;
        u.visasTill = e.getVisasTill();
        u.innehall = e.getInnehall();

        List<UtskickBilaga> bilagor = new ArrayList<>();
        for (UtskickBilagaEntity b : e.getBilagor()) {
            bilagor.add(new UtskickBilaga(b.getFilnamn(), b.getUrl()));
        }
        u.bilagor = bilagor;
        return u;
    }

    private Dokument toDokument(DokumentEntity e) {
        Dokument d = new Dokument();
        d.dokumentnamn = e.getDokumentnamn();
        d.forbindelse = e.getForbindelse();
        d.dokumentdatum = e.getDokumentdatum();
        d.utskicksdatum = e.getUtskicksdatum();
        d.skickatsTill = e.getSkickatsTill();
        d.visasTill = e.getVisasTill();
        d.last = e.getLast() != null && e.getLast() == 1;
        d.borttaget = e.getBorttaget() != null && e.getBorttaget() == 1;
        d.arkiverat = e.getArkiverat() != null && e.getArkiverat() == 1;
        return d;
    }

    private Kuvert toKuvert(KuvertEntity e) {
        return new Kuvert(e.getKuvertId(), e.getKundnr(), e.getKundnamn(),
                e.getDatum(), e.getMall(), e.getKanal(), e.getStatus());
    }

    private UtskickInstallning toUtskickInstallning(UtskickInstallningEntity e) {
        return new UtskickInstallning(
                e.getKategori(), e.getAvser(), e.getForbindelse(),
                e.getPapper() != null && e.getPapper() == 1,
                e.getInternet() != null && e.getInternet() == 1);
    }

    private Informationssamband toInformationssamband(InformationssambandEntity e) {
        return new Informationssamband(e.getId(), e.getSystembeteckning(),
                e.getInformationsId(),
                e.getPubliceraAutomatiskt() != null && e.getPubliceraAutomatiskt() == 1);
    }

    private PubliceringJobb toPubliceringJobb(PubliceringJobbEntity e) {
        String leverans = e.getLeveranstidpunkt() != null ? e.getLeveranstidpunkt().toString() : null;
        return new PubliceringJobb(e.getJobbId(), e.getSystembeteckning(),
                e.getInformationsId(), leverans, e.getStatus());
    }

    private Informationsprodukt toInformationsprodukt(InformationsproduktEntity e) {
        Informationsprodukt p = new Informationsprodukt();
        p.id = e.getId();
        p.namn = e.getNamn();
        p.land = e.getLand();
        p.status = e.getStatus();
        p.notifieringskategori = e.getNotifieringskategori();
        p.systembeteckning = e.getSystembeteckning();
        p.insynsskyddad = e.getInsynsskyddad() != null && e.getInsynsskyddad() == 1;
        p.defaultkanaler = new Kanaler(
                e.getDefaultPapper() != null && e.getDefaultPapper() == 1,
                e.getDefaultInternet() != null && e.getDefaultInternet() == 1);
        p.tillåtnaKanaler = new Kanaler(
                e.getTilllåtnaPapper() != null && e.getTilllåtnaPapper() == 1,
                e.getTilllåtnaInternet() != null && e.getTilllåtnaInternet() == 1);
        p.obligatoriskaKanaler = new Kanaler(
                e.getObligatoriskaPapper() != null && e.getObligatoriskaPapper() == 1,
                e.getObligatoriskaInternet() != null && e.getObligatoriskaInternet() == 1);
        p.avgiftsidPapper = e.getAvgiftsidPapper();
        p.avgiftsidInternet = e.getAvgiftsidInternet();
        p.visningstidEArkivMan = e.getVisningstidEArkivMan() != null ? e.getVisningstidEArkivMan() : 0;
        p.lagringstidDiskMan = e.getLagringstidDiskMan() != null ? e.getLagringstidDiskMan() : 0;
        p.meddelandetext = e.getMeddelandetext();
        return p;
    }

    private void applyProduktInput(InformationsproduktEntity e, InformationsproduktInput input) {
        e.setNamn(input.namn);
        e.setLand(input.land);
        e.setStatus(input.status);
        e.setNotifieringskategori(input.notifieringskategori);
        e.setSystembeteckning(input.systembeteckning);
        e.setInsynsskyddad(input.insynsskyddad ? 1 : 0);
        if (input.defaultkanaler != null) {
            e.setDefaultPapper(input.defaultkanaler.papper ? 1 : 0);
            e.setDefaultInternet(input.defaultkanaler.internet ? 1 : 0);
        }
        if (input.tillåtnaKanaler != null) {
            e.setTilllåtnaPapper(input.tillåtnaKanaler.papper ? 1 : 0);
            e.setTilllåtnaInternet(input.tillåtnaKanaler.internet ? 1 : 0);
        }
        if (input.obligatoriskaKanaler != null) {
            e.setObligatoriskaPapper(input.obligatoriskaKanaler.papper ? 1 : 0);
            e.setObligatoriskaInternet(input.obligatoriskaKanaler.internet ? 1 : 0);
        }
        e.setAvgiftsidPapper(input.avgiftsidPapper);
        e.setAvgiftsidInternet(input.avgiftsidInternet);
        e.setVisningstidEArkivMan(input.visningstidEArkivMan);
        e.setLagringstidDiskMan(input.lagringstidDiskMan);
        e.setMeddelandetext(input.meddelandetext);
    }

    private Debiteringsuppgift toDebiteringsuppgift(DebiteringsuppgiftEntity e) {
        return new Debiteringsuppgift(e.getProduktid(), e.getMeddelandeid(),
                e.getSystembeteckning(), e.getAntsKodInternet(),
                e.getAntsKodEjInternet(), e.getResultatstalle());
    }

    private MassutskickItem toMassutskickItem(MassutskickEntity e) {
        return new MassutskickItem(e.getMeddId(), e.getLand(), e.getAvsandare(),
                e.getAmne(), e.getUtskicksdatum(), e.getNotifieringskategori(),
                e.getMeddelande(), e.getStatus());
    }
}
