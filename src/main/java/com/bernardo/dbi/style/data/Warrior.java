package com.bernardo.dbi.style.data;

import com.bernardo.dbi.style.IFightStyle;
import com.bernardo.dbi.style.data.StyleData;

public class Warrior implements IFightStyle {
    @Override
    public StyleData getStyleData() {
        StyleData data = new StyleData();
        // configurar status base do guerreiro
        data.str += 3;
        data.con += 2;
        data.dex -= 3;
        data.mnd += 2;
        data.will += 1;
        data.spi += 2;

        return data;
    }
}
