package com.dragonblockinfinity.race;

public enum Race {
    SAIYAN_PURE("Saiyan Pure", 10, 8, 12, 6, 5, 15),
    HALF_SAIYAN("Half-Saiyan", 8, 10, 10, 8, 7, 12),
    HUMAN("Human", 6, 8, 8, 5, 12, 8);

    private final String displayName;
    private final int baseStr, baseDex, baseCon, baseWill, baseMnd, baseSpi;

    Race(String displayName, int str, int dex, int con, int will, int mnd, int spi) {
        this.displayName = displayName;
        this.baseStr = str;
        this.baseDex = dex;
        this.baseCon = con;
        this.baseWill = will;
        this.baseMnd = mnd;
        this.baseSpi = spi;
    }

    public String getDisplayName() { return displayName; }
    public int getBaseStr() { return baseStr; }
    public int getBaseDex() { return baseDex; }
    public int getBaseCon() { return baseCon; }
    public int getBaseWill() { return baseWill; }
    public int getBaseMnd() { return baseMnd; }
    public int getBaseSpi() { return baseSpi; }
}
