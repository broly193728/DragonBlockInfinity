package com.bernardo.dbi.race;
import com.bernardo.dbi.status.StatusBaseMultiversal;
import com.bernardo.dbi.status.StatusMultiplier;

public class Arconsian {
    public String name = "Arconsian";
    public StatusMultiplier warrior;
    public StatusMultiplier spiritualist;
    public StatusMultiplier martialArts;

    public Arconsian() {
        warrior = new StatusMultiplier(1.5f, 1.2f, 1.4f, 1.1f, 1.0f, 1.1f);
        spiritualist = new StatusMultiplier(1.3f, 1.1f, 1.3f, 1.2f, 1.0f, 1.2f);
        martialArts = new StatusMultiplier(1.4f, 1.2f, 1.3f, 1.15f, 1.0f, 1.15f);
    }
}
