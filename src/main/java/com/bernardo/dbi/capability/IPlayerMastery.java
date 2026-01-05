package com.bernardo.dbi.capability;

public interface IPlayerMastery {

    float getMasteryLevel(String formId);
    void setMasteryLevel(String formId, float level);
    void addMasteryExp(String formId, float exp);

    void copyFrom(IPlayerMastery other);
}