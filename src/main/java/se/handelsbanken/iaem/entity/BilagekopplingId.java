package se.handelsbanken.iaem.entity;

import java.io.Serializable;
import java.util.Objects;

public class BilagekopplingId implements Serializable {
    private String persmeddid;
    private String bilaga;

    public BilagekopplingId() {}

    public BilagekopplingId(String persmeddid, String bilaga) {
        this.persmeddid = persmeddid;
        this.bilaga = bilaga;
    }

    public String getPersmeddid() { return persmeddid; }
    public String getBilaga() { return bilaga; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BilagekopplingId that)) return false;
        return Objects.equals(persmeddid, that.persmeddid) && Objects.equals(bilaga, that.bilaga);
    }

    @Override
    public int hashCode() { return Objects.hash(persmeddid, bilaga); }
}
