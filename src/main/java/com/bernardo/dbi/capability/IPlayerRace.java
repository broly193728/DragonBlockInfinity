package com.bernardo.dbi.capability;

import com.bernardo.dbi.race.Race;

public interface IPlayerRace {

    Race.RaceType getRace();
    void setRace(Race.RaceType race);

    void copyFrom(IPlayerRace other);
}