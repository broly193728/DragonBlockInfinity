package com.bernardo.dbi.capability;

import com.bernardo.dbi.style.FightStyle;
import java.util.Objects;
import net.minecraft.nbt.CompoundTag;

public class PlayerStyle implements IPlayerStyle {
    private FightStyle style = FightStyle.Warrior; // default

    @Override
    public FightStyle getStyle() {
        return style;
    }

    @Override
    public void setStyle(FightStyle style) {
        this.style = style;
    }

    @Override
    public void copyFrom(IPlayerStyle other) {
        this.style = other.getStyle();
    }

    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putString("style", Objects.requireNonNull(style.name()));
        return tag;
    }

    public void deserializeNBT(CompoundTag tag) {
        String styleName = tag.getString("style");
        try {
            this.style = FightStyle.valueOf(styleName);
        } catch (IllegalArgumentException e) {
            this.style = FightStyle.Warrior; // fallback
        }
    }
}