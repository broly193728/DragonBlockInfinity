package com.bernardo.dbi.capability;

import com.bernardo.dbi.race.Race;
import net.minecraft.nbt.CompoundTag;
import java.util.Objects;

public class PlayerRace implements IPlayerRace {
    private Race.RaceType race = Race.RaceType.Humano; // default

    @Override
    public Race.RaceType getRace() {
        return race;
    }

    @Override
    public void setRace(Race.RaceType race) {
        this.race = race;
    }

    @Override
    public void copyFrom(IPlayerRace other) {
        this.race = other.getRace();
    }

    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putString("race", Objects.requireNonNull(race.name()));
        return tag;
    }

    public void deserializeNBT(CompoundTag tag) {
        String raceName = tag.getString("race");
        try {
            this.race = Race.RaceType.valueOf(raceName);
        } catch (IllegalArgumentException e) {
            this.race = Race.RaceType.Humano; // fallback
        }
    }
}