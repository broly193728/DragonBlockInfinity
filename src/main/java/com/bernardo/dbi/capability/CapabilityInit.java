package com.bernardo.dbi.capability;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;

public class CapabilityInit {

    public static final Capability<IPlayerStatus> PLAYER_STATUS = CapabilityManager.get(new CapabilityToken<>() {});
    public static final Capability<IPlayerRace> PLAYER_RACE = CapabilityManager.get(new CapabilityToken<>() {});
    public static final Capability<IPlayerStyle> PLAYER_STYLE = CapabilityManager.get(new CapabilityToken<>() {});
    public static final Capability<IPlayerForm> PLAYER_FORM = CapabilityManager.get(new CapabilityToken<>() {});
    public static final Capability<IPlayerMastery> PLAYER_MASTERY = CapabilityManager.get(new CapabilityToken<>() {});

    public static void register() {
        // Registration is handled by CapabilityManager.get
    }
}