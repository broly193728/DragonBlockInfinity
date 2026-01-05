package com.bernardo.dbi.status.stats;

public class Dex {
    public static double calculateDamage(double DexReal, double DamageReal) {
        if (DamageReal == 0) return 1.0;

        double ratio = DexReal / DamageReal;

        double finalDamage;
        if (ratio >= 1) {
            finalDamage = DamageReal * (1.0 / ratio);
        } else {
            finalDamage = DamageReal;
        }

        if (finalDamage < 1) {
            finalDamage = 1;
        }

        return finalDamage;
}
}
