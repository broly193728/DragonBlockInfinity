package com.bernardo.dbi.race;
import com.bernardo.dbi.status.StatusBaseMultiversal;
import com.bernardo.dbi.status.StatusMultiplier;

public class Namekian {
    public String name = "Namekian";
    public StatusMultiplier warrior;
    public StatusMultiplier spiritualist;
    public StatusMultiplier martialArts;

    public Namekian() {
        warrior = new StatusMultiplier(1.2f, 1.3f, 1.5f, 1.1f, 1.0f, 1.3f);
        spiritualist = new StatusMultiplier(1.0f, 1.2f, 1.4f, 1.4f, 1.0f, 1.5f);
        martialArts = new StatusMultiplier(1.15f, 1.25f, 1.4f, 1.2f, 1.0f, 1.3f);
    }
}
