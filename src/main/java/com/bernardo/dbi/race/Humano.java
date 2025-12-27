package com.bernardo.dbi.race;
import com.bernardo.dbi.status.StatusBaseMultiversal;
import com.bernardo.dbi.status.StatusMultiplier;

public class Humano {
    public String name = "Humano";
    public StatusMultiplier warrior;
    public StatusMultiplier spiritualist;
    public StatusMultiplier martialArts;

    public Humano() {
        warrior = new StatusMultiplier(1.3f, 1.3f, 1.2f, 1.1f, 1.0f, 1.1f);
        spiritualist = new StatusMultiplier(1.1f, 1.2f, 1.2f, 1.3f, 1.0f, 1.3f);
        martialArts = new StatusMultiplier(1.2f, 1.3f, 1.2f, 1.2f, 1.0f, 1.2f);
    }
}
