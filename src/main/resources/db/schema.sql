-- EpaTraktor database schema
-- Based on EpaTraktor.pptx data model

CREATE SCHEMA IF NOT EXISTS epatraktor;
SET search_path TO epatraktor;

-- Lookup: Meddelandetyp
CREATE TABLE meddelandetyp (
    persmeddtyp     NUMERIC(2,0)  NOT NULL,
    beskrivning     VARCHAR(80),
    CONSTRAINT pk_meddelandetyp PRIMARY KEY (persmeddtyp)
);

-- Lookup: Meddelandestatus
CREATE TABLE meddelandestatus (
    persmeddstatus  NUMERIC(2,0)  NOT NULL,
    beskrivning     VARCHAR(80),
    CONSTRAINT pk_meddelandestatus PRIMARY KEY (persmeddstatus)
);

-- Lookup: Frågekategori
CREATE TABLE fragekategori (
    meddfrågekategori  NUMERIC(2,0)  NOT NULL,
    kategoritext       VARCHAR(80),
    CONSTRAINT pk_fragekategori PRIMARY KEY (meddfrågekategori)
);

-- Main: Meddelande_Header
CREATE TABLE meddelande_header (
    persmeddid          CHAR(32)      NOT NULL,
    tread               CHAR(32),
    skapdatum           TIMESTAMP,
    persmeddtyp         NUMERIC(2,0),
    mottagare           VARCHAR(20),
    avsandare           VARCHAR(20),
    avsandarklartext    VARCHAR(200),
    borttagetvmot       NUMERIC(1,0)  DEFAULT 0,
    borttagetvavs       NUMERIC(1,0)  DEFAULT 0,
    persmeddstatus      NUMERIC(2,0),
    statusfrandatum     TIMESTAMP,
    handlaggare         VARCHAR(8),
    svarsmottagare      NUMERIC(15),
    rubrik              VARCHAR(320),
    kundnummer          VARCHAR(32),
    maxmeddelandeid     VARCHAR(32),
    clearingnummer      VARCHAR(32),
    mottagarlarlest     TIMESTAMP,
    mottagarklartext    VARCHAR(200),
    meddelandeordernr   NUMERIC(4,0),
    meddfrågekategori   NUMERIC(2,0),
    mottagarekund       NUMERIC(1,0)  DEFAULT 0,
    avsandarekund       NUMERIC(1,0)  DEFAULT 0,
    insynsskyddat       NUMERIC(1,0)  DEFAULT 0,
    arkiverat           NUMERIC(1,0)  DEFAULT 0,
    kanalinredd         NUMERIC(1,0)  DEFAULT 0,
    bilaga              NUMERIC(1,0)  DEFAULT 0,
    clearingrefid       CHAR(11),
    visastill           TIMESTAMP,
    isocountrycode      CHAR(3),
    assgmid             VARCHAR(70),
    rubrik_nr           NUMERIC(5,0),
    meddeland_nr        NUMERIC(5,0),
    mediatyp            CHAR(1),
    CONSTRAINT pk_meddelande_header    PRIMARY KEY (persmeddid),
    CONSTRAINT fk_mh_typ               FOREIGN KEY (persmeddtyp)       REFERENCES meddelandetyp(persmeddtyp),
    CONSTRAINT fk_mh_status            FOREIGN KEY (persmeddstatus)    REFERENCES meddelandestatus(persmeddstatus),
    CONSTRAINT fk_mh_fragekategori     FOREIGN KEY (meddfrågekategori) REFERENCES fragekategori(meddfrågekategori)
);

-- Meddelandetext (body)
CREATE TABLE meddelandetext (
    persmeddid      CHAR(32)        NOT NULL,
    brodtext        VARCHAR(23000),
    signeringsfil   VARCHAR(32),
    CONSTRAINT pk_meddelandetext  PRIMARY KEY (persmeddid),
    CONSTRAINT fk_mt_header       FOREIGN KEY (persmeddid) REFERENCES meddelande_header(persmeddid)
);

-- Bilaga (attachment metadata + content)
CREATE TABLE bilaga (
    bilaga_id           BIGSERIAL       NOT NULL,
    meddelandeid        VARCHAR(32),
    bilaga_ref          VARCHAR(32),
    filnamn             VARCHAR(500),
    mimetype            VARCHAR(256),
    signeringshash      VARCHAR(40),
    virusscanstatus     VARCHAR(32),
    filstorlek          INTEGER,
    fil                 BYTEA,
    uppladdningsdatum   TIMESTAMP,
    CONSTRAINT pk_bilaga PRIMARY KEY (bilaga_id)
);

-- Bilagekoppling (attachment link)
CREATE TABLE bilagekoppling (
    persmeddid  CHAR(32)    NOT NULL,
    bilaga      VARCHAR(32) NOT NULL,
    CONSTRAINT pk_bilagekoppling  PRIMARY KEY (persmeddid, bilaga),
    CONSTRAINT fk_bk_header       FOREIGN KEY (persmeddid) REFERENCES meddelande_header(persmeddid)
);

-- Indexes
CREATE INDEX idx_mh_kundnummer     ON meddelande_header(kundnummer);
CREATE INDEX idx_mh_mottagare      ON meddelande_header(mottagare);
CREATE INDEX idx_mh_avsandare      ON meddelande_header(avsandare);
CREATE INDEX idx_mh_skapdatum      ON meddelande_header(skapdatum);
CREATE INDEX idx_mh_status         ON meddelande_header(persmeddstatus);
CREATE INDEX idx_bilaga_meddid     ON bilaga(meddelandeid);

GRANT ALL ON SCHEMA epatraktor TO epatraktor;
GRANT ALL ON ALL TABLES IN SCHEMA epatraktor TO epatraktor;
GRANT ALL ON ALL SEQUENCES IN SCHEMA epatraktor TO epatraktor;

-- =============================================================
-- New tables for mock data domains
-- =============================================================

-- Add missing columns to meddelande_header
ALTER TABLE meddelande_header
    ADD COLUMN IF NOT EXISTS las NUMERIC(1,0) DEFAULT 0,
    ADD COLUMN IF NOT EXISTS kategori VARCHAR(100),
    ADD COLUMN IF NOT EXISTS kontor VARCHAR(32),
    ADD COLUMN IF NOT EXISTS kundnamn VARCHAR(200);

-- Kund
CREATE TABLE IF NOT EXISTS kund (
    kundnr      VARCHAR(32)     NOT NULL,
    kundnamn    VARCHAR(200),
    land        VARCHAR(100),
    CONSTRAINT pk_kund PRIMARY KEY (kundnr)
);

-- Utskick
CREATE TABLE IF NOT EXISTS utskick (
    utskick_id  VARCHAR(32)     NOT NULL,
    kundnr      VARCHAR(32),
    kundnamn    VARCHAR(200),
    avsandare   VARCHAR(200),
    mottagare   VARCHAR(200),
    datum       TIMESTAMP,
    kategori    VARCHAR(100),
    amne        VARCHAR(500),
    las         NUMERIC(1,0)    DEFAULT 0,
    borttaget   NUMERIC(1,0)    DEFAULT 0,
    arkiverat   NUMERIC(1,0)    DEFAULT 0,
    visas_till  VARCHAR(20),
    innehall    TEXT,
    CONSTRAINT pk_utskick PRIMARY KEY (utskick_id)
);

-- Utskick bilaga
CREATE TABLE IF NOT EXISTS utskick_bilaga (
    id          BIGSERIAL       NOT NULL,
    utskick_id  VARCHAR(32),
    filnamn     VARCHAR(500),
    url         VARCHAR(500),
    CONSTRAINT pk_utskick_bilaga PRIMARY KEY (id)
);

-- Dokument
CREATE TABLE IF NOT EXISTS dokument (
    id              BIGSERIAL       NOT NULL,
    kundnr          VARCHAR(32),
    dokumentnamn    VARCHAR(500),
    forbindelse     VARCHAR(100),
    dokumentdatum   VARCHAR(20),
    utskicksdatum   VARCHAR(20),
    skickats_till   VARCHAR(50),
    visas_till      VARCHAR(20),
    last            NUMERIC(1,0)    DEFAULT 0,
    borttaget       NUMERIC(1,0)    DEFAULT 0,
    arkiverat       NUMERIC(1,0)    DEFAULT 0,
    CONSTRAINT pk_dokument PRIMARY KEY (id)
);

-- Kuvert
CREATE TABLE IF NOT EXISTS kuvert (
    kuvert_id   VARCHAR(32)     NOT NULL,
    kundnr      VARCHAR(32),
    kundnamn    VARCHAR(200),
    datum       VARCHAR(20),
    mall        VARCHAR(100),
    kanal       VARCHAR(100),
    status      VARCHAR(50),
    CONSTRAINT pk_kuvert PRIMARY KEY (kuvert_id)
);

-- Utskick installning
CREATE TABLE IF NOT EXISTS utskick_installning (
    id          BIGSERIAL       NOT NULL,
    kundnr      VARCHAR(32),
    kategori    VARCHAR(100),
    avser       VARCHAR(100),
    forbindelse VARCHAR(100),
    papper      NUMERIC(1,0)    DEFAULT 0,
    internet    NUMERIC(1,0)    DEFAULT 0,
    CONSTRAINT pk_utskick_installning PRIMARY KEY (id)
);

-- Informationssamband
CREATE TABLE IF NOT EXISTS informationssamband (
    id                      VARCHAR(32)     NOT NULL,
    systembeteckning        VARCHAR(100),
    informations_id         VARCHAR(100),
    publicera_automatiskt   NUMERIC(1,0)    DEFAULT 0,
    CONSTRAINT pk_informationssamband PRIMARY KEY (id)
);

-- Publicering jobb
CREATE TABLE IF NOT EXISTS publicering_jobb (
    jobb_id             VARCHAR(32)     NOT NULL,
    systembeteckning    VARCHAR(100),
    informations_id     VARCHAR(100),
    leveranstidpunkt    TIMESTAMP,
    status              VARCHAR(50),
    CONSTRAINT pk_publicering_jobb PRIMARY KEY (jobb_id)
);

-- Informationsprodukt
CREATE TABLE IF NOT EXISTS informationsprodukt (
    id                      VARCHAR(32)     NOT NULL,
    namn                    VARCHAR(200),
    land                    VARCHAR(100),
    status                  VARCHAR(50),
    notifieringskategori    VARCHAR(200),
    systembeteckning        VARCHAR(50),
    insynsskyddad           NUMERIC(1,0)    DEFAULT 0,
    default_papper          NUMERIC(1,0)    DEFAULT 0,
    default_internet        NUMERIC(1,0)    DEFAULT 0,
    tilllatna_papper        NUMERIC(1,0)    DEFAULT 0,
    tilllatna_internet      NUMERIC(1,0)    DEFAULT 0,
    obligatoriska_papper    NUMERIC(1,0)    DEFAULT 0,
    obligatoriska_internet  NUMERIC(1,0)    DEFAULT 0,
    avgiftsid_papper        VARCHAR(50),
    avgiftsid_internet      VARCHAR(50),
    visningstid_earkiv_man  INTEGER,
    lagringstid_disk_man    INTEGER,
    meddelandetext          TEXT,
    CONSTRAINT pk_informationsprodukt PRIMARY KEY (id)
);

-- Debiteringsuppgift
CREATE TABLE IF NOT EXISTS debiteringsuppgift (
    produktid               VARCHAR(50)     NOT NULL,
    meddelandeid            VARCHAR(50),
    systembeteckning        VARCHAR(50),
    ants_kod_internet       VARCHAR(50),
    ants_kod_ej_internet    VARCHAR(50),
    resultatstalle          VARCHAR(50),
    CONSTRAINT pk_debiteringsuppgift PRIMARY KEY (produktid)
);

-- Massutskick
CREATE TABLE IF NOT EXISTS massutskick (
    medd_id                 VARCHAR(50)     NOT NULL,
    land                    VARCHAR(100),
    avsandare               VARCHAR(200),
    amne                    VARCHAR(500),
    utskicksdatum           VARCHAR(20),
    notifieringskategori    VARCHAR(200),
    meddelande              TEXT,
    status                  VARCHAR(50),
    CONSTRAINT pk_massutskick PRIMARY KEY (medd_id)
);

GRANT ALL ON ALL TABLES IN SCHEMA epatraktor TO epatraktor;
GRANT ALL ON ALL SEQUENCES IN SCHEMA epatraktor TO epatraktor;
