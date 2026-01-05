package com.bernardo.dbi.capability;

import com.bernardo.dbi.style.FightStyle;

public interface IPlayerStyle {

    FightStyle getStyle();
    void setStyle(FightStyle style);

    void copyFrom(IPlayerStyle other);
}