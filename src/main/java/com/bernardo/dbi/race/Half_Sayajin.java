package com.bernardo.dbi.race;
import com.bernardo.dbi.status.StatusBaseMultiversal;
import com.bernardo.dbi.status.StatusMultiplier;

public class Half_Sayajin {
    public String name = "Half_Sayajin";
    public StatusMultiplier warrior;
    public StatusMultiplier spiritualist;
    public StatusMultiplier martialArts;

    public Half_Sayajin() {
        warrior = new StatusMultiplier(1.35f, 1.4f, 1.25f, 1.2f, 1.0f, 1.3f);
        spiritualist = new StatusMultiplier(1.15f, 1.3f, 1.25f, 1.4f, 1.0f, 1.45f);
        martialArts = new StatusMultiplier(1.3f, 1.3f, 1.25f, 1.3f, 1.0f, 1.3f);
    }
}
