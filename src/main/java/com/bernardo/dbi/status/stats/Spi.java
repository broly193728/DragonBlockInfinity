package com.bernardo.dbi.status.stats;

public class Spi {

    // KI base do mod
    public static final double BASE_KI = 1000.0;

            // Calcula KI máximo
    public static double maxKi(double spiReal) {
        return BASE_KI + (spiReal * 5.0);
    }
}