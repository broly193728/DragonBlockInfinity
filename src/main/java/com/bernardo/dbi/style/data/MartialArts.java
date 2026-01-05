package com.bernardo.dbi.style.data;

import com.bernardo.dbi.style.IFightStyle;
import com.bernardo.dbi.style.data.StyleData;

public class MartialArts implements IFightStyle {
    @Override
    public StyleData getStyleData() {
        StyleData data = new StyleData();

        data.str += 2;
        data.dex += 2;
        data.con += 2;
        data.will += 2;
        data.mnd += 2;
        data.spi += 2;
        return data;
    }
}