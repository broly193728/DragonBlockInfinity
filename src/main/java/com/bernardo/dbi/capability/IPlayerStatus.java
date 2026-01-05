package com.bernardo.dbi.capability;

public interface IPlayerStatus {

    int getStr();
    void setStr(int value);

    int getDex();
    void setDex(int value);

    int getCon();
    void setCon(int value);

    int getSpi();
    void setSpi(int value);

    int getWill();
    void setWill(int value);

    int getMnd();
    void setMnd(int value);

    void copyFrom(IPlayerStatus other);
}