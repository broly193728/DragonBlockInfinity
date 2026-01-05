package com.bernardo.dbi.status.stats;

public class Str {

    // dano base do soco (exemplo)
    public static final double BASE_DAMAGE = 1.0;

            // calcula dano real baseado no STR
    public static double calculateDamage(double strReal) {
        return BASE_DAMAGE + (strReal * 0.5);
    }
}