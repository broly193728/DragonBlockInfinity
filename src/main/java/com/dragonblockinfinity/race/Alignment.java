package com.dragonblockinfinity.race;

public enum Alignment {
    GOOD("Good"),
    NEUTRAL("Neutral"),
    EVIL("Evil");

    private final String displayName;

    Alignment(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() { return displayName; }
}
