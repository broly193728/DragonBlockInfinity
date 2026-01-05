package com.bernardo.dbi.status.stats;

public class Con {

    // Vida base fixa do mod
    public static final double BASE_HEALTH = 200.0;
    public static final double BASE_STAMINA = 100.0;

                // Calcula vida máxima
    public static double maxHealth(double conReal) {
    return BASE_HEALTH + (conReal * 2.0);
                                }

                                    // Calcula stamina máxima
    public static double maxStamina(double conReal) {
    return BASE_STAMINA + conReal;
    }
}