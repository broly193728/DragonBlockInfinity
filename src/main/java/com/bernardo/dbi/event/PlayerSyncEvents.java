package com.bernardo.dbi.event;

import com.bernardo.dbi.capability.PlayerStatusProvider;
import com.bernardo.dbi.capability.PlayerRaceProvider;
import com.bernardo.dbi.capability.PlayerStyleProvider;
import com.bernardo.dbi.capability.PlayerFormProvider;
import com.bernardo.dbi.capability.PlayerMasteryProvider;
import com.bernardo.dbi.network.ModNetwork;
import com.bernardo.dbi.network.packet.SyncPlayerStatusS2CPacket;
import com.bernardo.dbi.network.packet.SyncPlayerRaceS2CPacket;
import com.bernardo.dbi.network.packet.SyncPlayerStyleS2CPacket;
import com.bernardo.dbi.network.packet.SyncPlayerFormS2CPacket;
import com.bernardo.dbi.network.packet.SyncPlayerMasteryS2CPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class PlayerSyncEvents {

    @SubscribeEvent
    public static void onLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;

        SyncPlayerStatusS2CPacket.sync(player);
        SyncPlayerRaceS2CPacket.sync(player);
        SyncPlayerStyleS2CPacket.sync(player);
        SyncPlayerFormS2CPacket.sync(player);
        SyncPlayerMasteryS2CPacket.sync(player);
    }

    @SubscribeEvent
    public static void onRespawn(PlayerEvent.PlayerRespawnEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;

        SyncPlayerStatusS2CPacket.sync(player);
        SyncPlayerRaceS2CPacket.sync(player);
        SyncPlayerStyleS2CPacket.sync(player);
        SyncPlayerFormS2CPacket.sync(player);
        SyncPlayerMasteryS2CPacket.sync(player);
    }

    @SubscribeEvent
    public static void onDimensionChange(PlayerEvent.PlayerChangedDimensionEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;

        SyncPlayerStatusS2CPacket.sync(player);
        SyncPlayerRaceS2CPacket.sync(player);
        SyncPlayerStyleS2CPacket.sync(player);
        SyncPlayerFormS2CPacket.sync(player);
        SyncPlayerMasteryS2CPacket.sync(player);
    }
}
