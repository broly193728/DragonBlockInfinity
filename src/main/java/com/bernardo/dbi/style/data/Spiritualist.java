package com.bernardo.dbi.style.data;

import com.bernardo.dbi.style.IFightStyle;
import com.bernardo.dbi.style.data.StyleData;

public class Spiritualist implements IFightStyle {
    @Override
    public StyleData getStyleData() {
        StyleData data = new StyleData();

        data.spi += 3;
        data.mnd += 2;
        data.will += 1;
        data.str += 2;
        data.dex += 1;
        data.con += 2;
        return data;
    }
}
