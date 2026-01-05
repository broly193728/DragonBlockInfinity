package com.bernardo.dbi.capability;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;

public class PlayerFormProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static final Capability<IPlayerForm> FORM = CapabilityManager.get(new CapabilityToken<IPlayerForm>() {});

    private final PlayerForm backend = new PlayerForm();
    private final LazyOptional<IPlayerForm> optional = LazyOptional.of(() -> backend);

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        return cap == FORM ? optional.cast() : LazyOptional.empty();
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