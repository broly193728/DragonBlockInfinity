package com.bernardo.dbi.race;

import net.minecraft.resources.ResourceLocation;
import com.bernardo.dbi.status.StatusBaseMultiversal;
import com.bernardo.dbi.status.StatusMultiplier;



public class Sayajin {

    public String name = "Sayajin";

            // multiplicadores por estilo
    public StatusMultiplier warrior;
    public StatusMultiplier spiritualist;
    public StatusMultiplier martialArts;

    public Sayajin() {
             // Warrior
    warrior = new StatusMultiplier(
        1.4f,  // str
        1.35f, // dex
        1.2f,  // con
        1.15f, // will
        1.0f,  // mnd
        1.25f  // spi
        );
             // Spiritualist
    spiritualist = new StatusMultiplier(
        1.1f,  // str
        1.25f, // dex
        1.2f,  // con
        1.35f, // will
        1.0f,  // mnd
        1.4f   // spi
        );

        // Martial Arts
    martialArts = new StatusMultiplier(
        1.25f, // str
        1.25f, // dex
        1.2f,  // con
        1.25f, // will
        1.0f,  // mnd
        1.25f  // spi
        );
    };
}