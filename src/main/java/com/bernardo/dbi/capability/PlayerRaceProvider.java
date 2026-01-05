package com.bernardo.dbi.capability;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;

public class PlayerRaceProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static final Capability<IPlayerRace> RACE = CapabilityManager.get(new CapabilityToken<IPlayerRace>() {});

    private final PlayerRace backend = new PlayerRace();
    private final LazyOptional<IPlayerRace> optional = LazyOptional.of(() -> backend);

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        return cap == RACE ? optional.cast() : LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        return backend.serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundTag tag) {
        backend.deserializeNBT(tag);
    }
}